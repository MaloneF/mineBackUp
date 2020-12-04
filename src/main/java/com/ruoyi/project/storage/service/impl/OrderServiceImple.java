package com.ruoyi.project.storage.service.impl;

import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.common.enums.RecordPointWay;
import com.ruoyi.project.common.enums.TerminalEnum;
import com.ruoyi.project.common.util.SeqGeneratorUtil;
import com.ruoyi.project.storage.domain.CustomerEntity;
import com.ruoyi.project.storage.domain.TBoxInfo;
import com.ruoyi.project.storage.domain.TOrder;
import com.ruoyi.project.storage.mapper.CustomerMapper;
import com.ruoyi.project.storage.mapper.PointMapper;
import com.ruoyi.project.storage.mapper.TBoxInfoMapper;
import com.ruoyi.project.storage.mapper.TOrderDao;
import com.ruoyi.project.storage.service.IboxService;
import com.ruoyi.project.storage.service.OrderService;
import com.ruoyi.project.storage.service.PointService;
import com.ruoyi.project.storage.utils.DataHandleUtil;
import com.ruoyi.project.storage.utils.ParameterUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * OrderServiceImple
 *
 * @author 马龙飞
 * @date 2020/12/2 14:59
 * <p>
 * 版本        修改时间        作者        修改内容
 * V1.0        2020/12/2     马龙飞        初始版本
 */
@Service
@Slf4j
public class OrderServiceImple implements OrderService {
    @Resource
    private TOrderDao orderDao;
    @Resource
    private IboxService boxService;
    @Resource
    private TBoxInfoMapper boxInfoMapper;
    @Resource
    private CustomerMapper customerMapper;
    @Resource
    private PointService pointService;

    @Override
    public TOrder getOrder(Long id) {
        return orderDao.queryById(id);
    }

    /**
     * 查询所有订单
     *
     * @param order 订单实体类
     * @return int
     */
    @Override
    public List<TOrder> getOrderList(TOrder order) {


        return orderDao.queryAll(order);
    }

    /**
     * 预约箱子
     *
     * @param tOrder 订单实体类
     * @return int
     */
//也可以把列表取出来，在java中进行操作，利用递归进行补偿机制，如果设置失败，则重新调用该方法设置。
    @Override
    public int orderBox(TOrder tOrder) {
        //判断用户积分是否足够
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setUserId(SecurityUtils.getUserId());
        customerEntity.setUserType("02");
        CustomerEntity customerEntity1 = customerMapper.selectCustomerById(customerEntity);
        Long currentPoints = customerEntity1.getCurrentPoints();
        Long needPoints = tOrder.getTotalPrice();
        if (currentPoints >= needPoints) {

            customerEntity1.setCurrentPoints(currentPoints - needPoints);

            int count1 = customerMapper.updateCustomer(customerEntity1);
            if (0 == count1) {

                log.error("修改信息失败:乐观锁");
                throw new CustomException("用户被其他人更改，请稍后再试", HttpStatus.CONFLICT);
            }
            ParameterUtil.setCreateEntity(tOrder);
            tOrder.setBackendDelFlag("0");
            tOrder.setAppDelFlag("0");
            tOrder.setStatus(1L);
            tOrder.setOrderCode(SeqGeneratorUtil.seqGenerator(DataHandleUtil.toCode(), 6));
            TBoxInfo boxInfo = boxInfoMapper.selectWhenOrder(tOrder.getBoxStandard());
//        boxInfo.setRemark(tOrder.getOrderName());
//        boxInfo.setBoxStandard(tOrder.getBoxStandard());
//        boxInfo.setBoxUnitPrice(tOrder.getBoxUnitPrice());
//        boxService.insertBoxInfo(boxInfo);
            log.info("测试信息{}", SecurityUtils.getUserId());
            tOrder.setBoxId(boxInfo.getId());
            tOrder.setBoxCode(boxInfo.getBoxCode());
            tOrder.setUserId(SecurityUtils.getUserId());
            int count = pointService.registerCustomerPoints(RecordPointWay.ORDER, SecurityUtils.getUserId(), -needPoints, null, Long.valueOf(tOrder.getOrderCode()));
            if (0 == count) {

                log.error("修改信息失败:乐观锁");
                throw new CustomException("用户被其他人更改，请稍后再试", HttpStatus.CONFLICT);
            }
            int count2 = boxInfoMapper.updateWhenOrder(boxInfo.getId());

            if (0 == count2) {

                log.error("修改信息失败:乐观锁");
                throw new CustomException("用户被其他人更改，请稍后再试", HttpStatus.CONFLICT);
            }
            return orderDao.insert(tOrder);
        } else {

            throw new CustomException("您的积分不足，需要的积分为【" + tOrder.getTotalPrice() + "】您的积分为【" + customerEntity1.getCurrentPoints() + "】");

        }


    }
}
