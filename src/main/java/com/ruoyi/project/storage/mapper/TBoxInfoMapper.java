package com.ruoyi.project.storage.mapper;

import com.ruoyi.project.storage.domain.TBoxInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.ptg.TblPtg;

import java.util.List;


/**
 * 箱子信息表(TBoxInfo)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-30 10:43:21
 */
public interface TBoxInfoMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TBoxInfo queryById(Object id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TBoxInfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param t 泛型实例对象
     * @return 对象列表
     */
    <T> List<T> queryAll(T t);

    /**
     * 新增数据
     *
     * @param tBoxInfo 实例对象
     * @return 影响行数
     */
    int insert(TBoxInfo tBoxInfo);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TBoxInfo> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TBoxInfo> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TBoxInfo> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<TBoxInfo> entities);

    /**
     * 修改数据
     *
     * @param tBoxInfo 实例对象
     * @return 影响行数
     */
    int update(TBoxInfo tBoxInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(List<Long> id);

    List<TBoxInfo> queryByIds(List<Long> ids);

    int deleteByBoxStandard(@Param("standList") List<String> standList);

    TBoxInfo  selectWhenOrder(String boxstandard);
    int updateWhenOrder(Long id);
}