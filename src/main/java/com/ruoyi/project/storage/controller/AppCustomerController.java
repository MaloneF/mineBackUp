package com.ruoyi.project.storage.controller;

import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.enums.TerminalEnum;
import com.ruoyi.project.storage.domain.CustomerEntity;
import com.ruoyi.project.storage.mapper.PointMapper;
import com.ruoyi.project.storage.service.ICustomerService;
import com.ruoyi.project.storage.service.PointService;
import com.ruoyi.project.storage.utils.CheckUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.Authorization;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * AppCustomerController
 *
 * @author 马龙飞
 * @date 2020/11/27 18:52
 * <p>
 * 版本        修改时间        作者        修改内容
 * V1.0        2020/11/27     马龙飞        初始版本
 */
@RestController
@RequestMapping("/app")

@Api(tags = {"【手机端】"}, description = "客户积分分页查询")
public class AppCustomerController extends BaseController {
    @Resource
    private ICustomerService customerService;
    @Resource
    private PointService pointService;
    /**
     * 查询用户积分记录
     */
    @GetMapping("/point/list")
    public TableDataInfo getCustomerPointList() {
        startPage();
        return getDataTable(pointService.selectdetailedPoint(SecurityUtils.getUserId()));
    }

    /**
     * 手机端进行用户注册
     */
    @PostMapping("/regist")
    public AjaxResult registerCustomerFromApp(@RequestBody CustomerEntity customerEntity) {
        //调用工具类进行唯一性检测
        AjaxResult ajaxResult = CheckUtil.checkUserUnique(customerEntity, 1);
        return ajaxResult == null ? toAjax(customerService.CustomerRegister(TerminalEnum.REGIST, customerEntity)) : null;
    }
    /**
     * 获取个人当前积分
     */
    @GetMapping("/point/select")
    public AjaxResult select() {

        return AjaxResult.success(pointService.selectPoint(SecurityUtils.getUserId()));
    }
    /**
     * 修改密码
     */
    @PutMapping("personal/updatePassword/{oldPassword}/{newPassword}")
    public AjaxResult updatePassword(@PathVariable("oldPassword")String oldPassWord,@PathVariable("newPassword")String newPassword){


        return  toAjax(customerService.updatePassword(TerminalEnum.REGIST,oldPassWord,newPassword)) ;


    }
}
