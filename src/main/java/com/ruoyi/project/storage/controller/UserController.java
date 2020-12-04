package com.ruoyi.project.storage.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.enums.TerminalEnum;
import com.ruoyi.project.storage.domain.CustomerEntity;
import com.ruoyi.project.storage.service.ICustomerService;
import com.ruoyi.project.storage.utils.CheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * UserController
 *
 * @author 马龙飞
 * @date 2020/11/28 22:32
 * <p>
 * 版本        修改时间        作者        修改内容
 * V1.0        2020/11/28     马龙飞        初始版本
 */
@RestController
@Slf4j
@RequestMapping("backend/user")
public class UserController extends BaseController {
    @Resource
    private ICustomerService customerService;

    @GetMapping("/list")
    /**
     * 查询用户信息
     */
    public TableDataInfo list(CustomerEntity customerEntity) {

        startPage();
        return getDataTable(customerService.selectCustomer(TerminalEnum.BACKEND, customerEntity));
    }

    /**
     * 后台创建用户
     */
    @PostMapping("/create")
    public AjaxResult createUser(@RequestBody CustomerEntity customerEntity) {

        AjaxResult ajaxResult = CheckUtil.checkUserUnique(customerEntity, 1);

        return ajaxResult == null ? toAjax(customerService.CustomerRegister(TerminalEnum.BACKEND, customerEntity)) : ajaxResult;
    }

    /**
     * 后台更新用户
     */
    @PutMapping("/update")
    public AjaxResult updateUser(@RequestBody CustomerEntity customerEntity) {

        AjaxResult ajaxResult = CheckUtil.checkUserUnique(customerEntity, 2);

        return ajaxResult == null ? toAjax(customerService.update(TerminalEnum.BACKEND, customerEntity)) : ajaxResult;
    }

    /**
     * 后台逻辑删除用户
     */
    @DeleteMapping("/delete/{userIds}")
    public AjaxResult remove(@PathVariable("userIds") List<Long> userIds) {

        return toAjax(customerService.delete(TerminalEnum.BACKEND,userIds));
    }
    /**
     * 后台重置用户密码
     */
    @PutMapping("reset/{userIds}")

    public AjaxResult resetPassword(@PathVariable("userIds") Long[] userIds) {

        AjaxResult ajaxResult = toAjax(customerService.resetPassword(TerminalEnum.BACKEND, userIds));

        return ajaxResult;
    }
    /**
     * 禁用/启用用户
     */
    @PutMapping("{operate}/{userIds}")
    public AjaxResult EnableOrDisableCustomer(@PathVariable("operate") String operate, @PathVariable("userIds") Long[] userIds) {


        return toAjax(customerService.enableOrDisableCustomer(TerminalEnum.BACKEND, operate, userIds));


    }





}
