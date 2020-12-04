package com.ruoyi.project.storage.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.storage.domain.TCustomerAddress;
import com.ruoyi.project.storage.mapper.TCustomerAddressDao;
import com.ruoyi.project.storage.service.TCustomerAddressService;
import com.ruoyi.project.storage.utils.ParameterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 客户地址表(TCustomerAddress)表服务实现类
 *
 * @author makejava
 * @since 2020-12-02 10:35:41
 */
@Service
@Slf4j
public class TCustomerAddressServiceImpl implements TCustomerAddressService {
    @Resource
    private TCustomerAddressDao tCustomerAddressDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AjaxResult queryById(Long id) {
        log.info("测试信息{}",id);
       if (null!=tCustomerAddressDao.queryById(id)){

           return AjaxResult.success(tCustomerAddressDao.queryById(id));

       }else {

           return AjaxResult.error();
       }


    }

    /**
     * 查询多条数据
     *
     * @param userId 查询起始位置
     * @return 对象列表
     */
    @Override
    public List<TCustomerAddress> queryAllByLimit(Long userId) {
        return this.tCustomerAddressDao.queryAllByLimit(userId);
    }

    /**
     * 新增数据
     *
     * @param tCustomerAddress 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(TCustomerAddress tCustomerAddress) {


        tCustomerAddress.setUserId(SecurityUtils.getUserId());
        ParameterUtil.setCreateEntity(tCustomerAddress);
        int i =tCustomerAddressDao.insert(tCustomerAddress);
        return i;
    }

    /**
     * 修改数据
     *
     * @param tCustomerAddress 实例对象
     * @return 实例对象
     */
    @Override
    public int update(TCustomerAddress tCustomerAddress) {
        ParameterUtil.setUpdateEntity(tCustomerAddress);
        return  tCustomerAddressDao.update(tCustomerAddress);

    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Object id) {
        return tCustomerAddressDao.deleteById(id) ;
    }
}