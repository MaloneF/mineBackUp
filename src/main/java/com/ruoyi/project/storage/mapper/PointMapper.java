package com.ruoyi.project.storage.mapper;

import com.ruoyi.project.storage.domain.CustomerEntity;
import com.ruoyi.project.storage.domain.PointEntity;

import java.util.List;
import java.util.Map;

/**
 * PointMapper
 *
 * @author 马龙飞
 * @date 2020/11/25 13:22
 * <p>
 * 版本        修改时间        作者        修改内容
 * V1.0        2020/11/25     马龙飞        初始版本
 */
public interface PointMapper {

    int registerCustomerPoints(PointEntity pointEntity);
    Long selectPointById(Long userId);
    List<PointEntity> selectdetailedPoint(Long userId);

}
