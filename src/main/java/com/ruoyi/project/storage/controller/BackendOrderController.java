package com.ruoyi.project.storage.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.storage.domain.TOrder;
import com.ruoyi.project.storage.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * BackendOrderController
 *
 * @author 马龙飞
 * @date 2020/12/4 8:57
 * <p>
 * 版本        修改时间        作者        修改内容
 * V1.0        2020/12/4     马龙飞        初始版本
 */
@RestController
@Slf4j
@RequestMapping("backend/order")
public class BackendOrderController extends BaseController {
    @Resource
    private OrderService orderService;

    @GetMapping("/list")
    public TableDataInfo GetOrderList(TOrder tOrder) {
        startPage();
        return getDataTable(orderService.getOrderList(tOrder));

    }
}
