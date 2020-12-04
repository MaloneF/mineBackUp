package com.ruoyi.project.storage.service.impl;

import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.common.enums.RecordPointWay;
import com.ruoyi.project.common.enums.TerminalEnum;
import com.ruoyi.project.storage.domain.PointEntity;
import com.ruoyi.project.storage.mapper.PointMapper;
import com.ruoyi.project.storage.service.PointService;
import com.ruoyi.project.storage.utils.ParameterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * PointServiceImple
 *
 * @author 马龙飞
 * @date 2020/11/25 18:40
 * <p>
 * 版本        修改时间        作者        修改内容
 * V1.0        2020/11/25     马龙飞        初始版本
 */
@Slf4j
@Service
public class PointServiceImple implements PointService {
    @Resource
    private PointMapper pointMapper;

    /**
     * 生成用户积分信息
     *
     * @param regist          获取积分方式
     * @param userId          用户ID
     * @param advertisementID 广告ID
     * @param orderId         订单ID
     * @return int
     */
    @Override
    public int registerCustomerPoints(RecordPointWay regist, Long userId, Long point, Long advertisementID, Long orderId) {

        PointEntity pointEntity = new PointEntity();
        //校验是否为注册类型

            //调用通用设置
            ParameterUtil.setRegistEntity(pointEntity);
            pointEntity.setWay(regist.getCode());
            pointEntity.setUserId(userId);
            pointEntity.setPoints(point);
            pointEntity.setAdvertisementId(advertisementID);
            pointEntity.setOrderId(orderId);
            log.error("用户类型{}", pointEntity.toString());

        return pointMapper.registerCustomerPoints(pointEntity);
    }

    /**
     * 查询积分详情
     *
     * @param userId 用户id
     * @return list
     */

    @Override
    public List<PointEntity> selectdetailedPoint(Long userId) {


        return pointMapper.selectdetailedPoint(userId);
    }
    /**
     * 查询当前积分
     *
     * @param userId 用户id
     * @return Long
     */
    @Override
    public Long selectPoint(Long userId) {
        return pointMapper.selectPointById(userId);
    }
}
