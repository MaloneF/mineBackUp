package com.ruoyi.project.storage.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.storage.domain.TCustomerAddress;
import com.ruoyi.project.storage.service.TCustomerAddressService;
import org.apache.commons.collections4.Put;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * AppAddressController
 *
 * @author 马龙飞
 * @date 2020/12/2 10:44
 * <p>
 * 版本        修改时间        作者        修改内容
 * V1.0        2020/12/2     马龙飞        初始版本
 */
@RestController
@RequestMapping("/app/address")
public class AppAddressController extends BaseController {

    @Resource
    private TCustomerAddressService tCustomerAddressService;

    /**
     * 获取用户地址
     */
    @GetMapping("/list")
    public TableDataInfo getAddressList() {
        startPage();
        return getDataTable(tCustomerAddressService.queryAllByLimit(SecurityUtils.getUserId()));
    }

    /**
     * 新建用户地址
     */
    @PostMapping("/create")
    public AjaxResult addAddress(@RequestBody TCustomerAddress tCustomerAddress) {

        return toAjax(tCustomerAddressService.insert(tCustomerAddress));


    }

    /**
     * 更新用户地址
     */
    @PutMapping("/update")

    public AjaxResult updateAddress(@RequestBody TCustomerAddress tCustomerAddress) {

        return toAjax(tCustomerAddressService.update(tCustomerAddress));


    }

    /**
     * 删除用户地址
     */
    @DeleteMapping("/delete/{id}")

    public AjaxResult deleteAddress(@PathVariable("id") Long id) {


        return toAjax(tCustomerAddressService.deleteById(id));
    }

}
