package com.ruoyi.project.storage.service;

import com.ruoyi.project.common.enums.RecordPointWay;
import com.ruoyi.project.common.enums.TerminalEnum;
import com.ruoyi.project.storage.domain.PointEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * pointService
 *
 * @author 马龙飞
 * @date 2020/11/25 11:50
 * <p>
 * 版本        修改时间        作者        修改内容
 * V1.0        2020/11/25     马龙飞        初始版本
 */
public interface PointService {


    int registerCustomerPoints(RecordPointWay regist, Long userId, Long point,Long advertisementID ,Long orderId);
    Long selectPoint(Long UserId);

   List<PointEntity> selectdetailedPoint(Long userId);
}
