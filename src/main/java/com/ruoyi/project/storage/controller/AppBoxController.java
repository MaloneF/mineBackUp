package com.ruoyi.project.storage.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.storage.domain.BoxStandardEntity;
import com.ruoyi.project.storage.domain.TOrder;
import com.ruoyi.project.storage.service.IboxService;
import com.ruoyi.project.storage.service.OrderService;
import com.ruoyi.project.storage.service.TCustomerAddressService;
import com.ruoyi.project.storage.utils.StatCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * AppBoxController
 *
 * @author 马龙飞
 * @date 2020/12/2 13:57
 * <p>
 * 版本        修改时间        作者        修改内容
 * V1.0        2020/12/2     马龙飞        初始版本
 */
@RestController
@Slf4j
@RequestMapping("app/box")
public class AppBoxController extends BaseController {
    @Resource
    private TCustomerAddressService tCustomerAddressService;
    @Resource
    private IboxService boxService;

    @Resource
    private OrderService orderService;

    /**
     * 获取默认地址
     */
    @GetMapping("/defaultAddress")
    public AjaxResult getDefaultAddress() {


        return tCustomerAddressService.queryById(SecurityUtils.getUserId());
    }

    /**
     * 获取可用的箱子规格
     */
    @GetMapping("/standard/select")

    public AjaxResult getBoxStandard(BoxStandardEntity boxStandardEntity) {


        return AjaxResult.success(boxService.getBoxStandardList(StatCode.BoxStatus, boxStandardEntity));
    }

    /**
     * 预约箱子
     */

    @PostMapping("/order")

    public AjaxResult order(@RequestBody TOrder tOrder) {

        return toAjax(orderService.orderBox(tOrder));

    }


}
