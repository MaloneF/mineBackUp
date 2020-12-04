package com.ruoyi.project.storage.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.enums.TerminalEnum;
import com.ruoyi.project.storage.domain.CustomerEntity;
import com.ruoyi.project.storage.service.ICustomerService;
import com.ruoyi.project.storage.utils.CheckUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * CustomerController
 *
 * @author 马龙飞
 * @date 2020/11/24 11:42
 * <p>
 * 版本        修改时间        作者        修改内容
 * V1.0        2020/11/24     马龙飞        初始版本
 */
@RestController
@RequestMapping("/backend/customer")
@Api(tags = {"【后台】客户管理"}, description = "客户列表分页查询，客户新增，客户删除，客户状态改变，客户修改，客户密码重置")
public class BackendCustomerController extends BaseController {
    @Resource
    private ICustomerService customerService;

    /**
     * 查询用户信息
     */
    @GetMapping("/list")
    public TableDataInfo list(CustomerEntity customerEntity) {

        startPage();
        return getDataTable(customerService.selectCustomer(TerminalEnum.APP, customerEntity));
    }

    /**
     * 新建手机端用户
     */
    @PostMapping("/create")
    public AjaxResult add(@RequestBody CustomerEntity customerEntity) {

        AjaxResult ajaxResult = CheckUtil.checkUserUnique(customerEntity, 1);

        return ajaxResult == null ? toAjax(customerService.CustomerRegister(TerminalEnum.APP, customerEntity)) : ajaxResult;
    }

    /**
     * 更新手机端用户信息
     */
    @PutMapping("/update")
    public AjaxResult update(@RequestBody CustomerEntity customerEntity) {


        AjaxResult ajaxResult = CheckUtil.checkUserUnique(customerEntity, 2);
        return ajaxResult == null ? toAjax(customerService.update(TerminalEnum.APP, customerEntity)) : ajaxResult;

    }

    /**
     * 逻辑删除手机端用户
     */
    @DeleteMapping("delete/{userIds}")
    public AjaxResult logicDelete(@PathVariable("userIds") List<Long> userIds) {
        AjaxResult ajaxResult = toAjax(customerService.delete(TerminalEnum.APP, userIds));
        return ajaxResult;
    }

    /**
     * 重置手机端用户密码
     */
    @PutMapping("reset/{userIds}")

    public AjaxResult resetPassword(@PathVariable("userIds") Long[] userIds) {

        AjaxResult ajaxResult = toAjax(customerService.resetPassword(TerminalEnum.APP, userIds));

        return ajaxResult;
    }

    /**
     * 禁用/启用用户
     */
    @PutMapping("{operate}/{userIds}")
    public AjaxResult EnableOrDisableCustomer(@PathVariable("operate") String operate, @PathVariable("userIds") Long[] userIds) {


        return toAjax(customerService.enableOrDisableCustomer(TerminalEnum.APP, operate, userIds));


    }



}




