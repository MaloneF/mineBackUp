package com.ruoyi.project.storage.mapper;

import com.ruoyi.project.common.enums.TerminalEnum;
import com.ruoyi.project.storage.domain.BoxStandardEntity;
import com.ruoyi.project.storage.domain.CustomerEntity;
import com.ruoyi.project.storage.domain.TBoxInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * BoxStandMapper
 *
 * @author 马龙飞
 * @date 2020/11/29 11:14
 * <p>
 * 版本        修改时间        作者        修改内容
 * V1.0        2020/11/29     马龙飞        初始版本
 */
public interface BoxStandMapper {
    /**
     * 查询箱子标准信息
     *
     * @param t 泛型
     * @return 实体类列表
     */
    <T> List<T> selectBoxStandard(T t);

    /**
     * 插入箱子标准
     *
     * @param t 泛型
     * @return 影响行数
     */
    <T> int insert(T t);

    /**
     * 查询箱子标准
     *
     * @param t 泛型
     * @return 箱子标准列表
     */

    <T> List<T> selectStandard();

    /**
     * 删除箱子标准
     *
     * @param ids id列表
     * @return 影响行数
     */
    int delete(List<Long> ids);

    /**
     * 更新箱子标准
     *
     * @param boxStandardEntity 箱子标准实体类
     * @return 影响行数
     */
    int increaseBoxStandard(BoxStandardEntity boxStandardEntity);

    /**
     * 通过箱子标准字段查询箱子标准
     *
     * @param boxStandard
     * @return BoxStandardEntity 实体类
     */
    BoxStandardEntity selectBoxStandardByBoxStandard(String boxStandard);

    /**
     * 更新箱子标准
     *
     * @param list 箱子标准
     * @return 影响行数
     */
    int decreaseBoxStandard(List<String> list);

    List<BoxStandardEntity> selectBoxStandardById(@Param("ids") List<Long> ids);

}
