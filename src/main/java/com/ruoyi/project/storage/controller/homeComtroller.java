package com.ruoyi.project.storage.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.common.enums.TerminalEnum;
import com.ruoyi.project.storage.service.ICustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * homeComtroller
 *
 * @author 马龙飞
 * @date 2020/11/29 10:20
 * <p>
 * 版本        修改时间        作者        修改内容
 * V1.0        2020/11/29     马龙飞        初始版本
 */
@RestController
@Slf4j
@RequestMapping("/backend/home/")
public class homeComtroller extends BaseController {
    @Resource
    private ICustomerService customerService;

    /**
     * 修改用户密码
     */

    @PutMapping("/updatePassword/{oldPassword}/{newPassword}")
    public AjaxResult updatePassword(@PathVariable("oldPassword") String oldPassWord, @PathVariable("newPassword") String newPassword) {


        return toAjax(customerService.updatePassword(TerminalEnum.BACKEND, oldPassWord, newPassword));


    }
}
