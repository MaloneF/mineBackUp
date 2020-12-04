package com.ruoyi.project.storage.service;

import com.ruoyi.project.storage.domain.TOrder;

import java.util.List;

/**
 * OrderService
 *
 * @author 马龙飞
 * @date 2020/12/2 14:58
 * <p>
 * 版本        修改时间        作者        修改内容
 * V1.0        2020/12/2     马龙飞        初始版本
 */
public interface OrderService {
    int orderBox(TOrder tOrder);

    List<TOrder> getOrderList(TOrder tOrder);
   TOrder  getOrder(Long id);
}
