package com.ruoyi.project.storage.service;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.storage.domain.TCustomerAddress;

import java.util.List;



/**
 * 客户地址表(TCustomerAddress)表服务接口
 *
 * @author makejava
 * @since 2020-12-02 10:35:40
 */
public interface TCustomerAddressService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AjaxResult queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TCustomerAddress> queryAllByLimit(Long UserId);

    /**
     * 新增数据
     *
     * @param tCustomerAddress 实例对象
     * @return 实例对象
     */
    int insert(TCustomerAddress tCustomerAddress);

    /**
     * 修改数据
     *
     * @param tCustomerAddress 实例对象
     * @return 实例对象
     */
    int update(TCustomerAddress tCustomerAddress);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
     int deleteById(Object id);

}