package com.ruoyi.project.storage.mapper;

import com.ruoyi.project.storage.domain.TCustomerAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 客户地址表(TCustomerAddress)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-02 10:35:39
 */
public interface TCustomerAddressDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TCustomerAddress queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TCustomerAddress> queryAllByLimit(Long userId);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tCustomerAddress 实例对象
     * @return 对象列表
     */
    List<TCustomerAddress> queryAll(TCustomerAddress tCustomerAddress);

    /**
     * 新增数据
     *
     * @param tCustomerAddress 实例对象
     * @return 影响行数
     */
    int insert(TCustomerAddress tCustomerAddress);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TCustomerAddress> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TCustomerAddress> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TCustomerAddress> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<TCustomerAddress> entities);

    /**
     * 修改数据
     *
     * @param tCustomerAddress 实例对象
     * @return 影响行数
     */
    int update(TCustomerAddress tCustomerAddress);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Object id);

}