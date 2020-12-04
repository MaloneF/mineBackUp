package com.ruoyi.project.storage.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.storage.domain.TOrder;
import com.ruoyi.project.storage.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * OrderController
 *
 * @author 马龙飞
 * @date 2020/12/2 16:09
 * <p>
 * 版本        修改时间        作者        修改内容
 * V1.0        2020/12/2     马龙飞        初始版本
 */
@RestController
@Slf4j
@RequestMapping("/app/order")
public class AppOrderController extends BaseController {
    @Resource
    private OrderService orderService;

    /**
     * 获取订单信息
     */

    @GetMapping("/list")
    public TableDataInfo getOrderList(TOrder tOrder) {
        tOrder.setUserId(SecurityUtils.getUserId());
        return getDataTable(orderService.getOrderList(tOrder));

    }

    /**
     * 获取订单详情
     */

    @GetMapping("/info/{id}")

    public AjaxResult getOrderDetails(@PathVariable("id") Long id) {

        return AjaxResult.success("查询成功",orderService.getOrder(id));

    }
}
