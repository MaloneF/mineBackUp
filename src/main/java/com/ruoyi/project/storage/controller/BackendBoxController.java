package com.ruoyi.project.storage.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.storage.domain.TBoxInfo;
import com.ruoyi.project.storage.service.IboxService;
import com.ruoyi.project.storage.utils.StatCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * BoxController
 *
 * @author 马龙飞
 * @date 2020/11/30 10:37
 * <p>
 * 版本        修改时间        作者        修改内容
 * V1.0        2020/11/30     马龙飞        初始版本
 */
@RestController
@Slf4j
@RequestMapping("/backend/box/info")
public class BackendBoxController extends BaseController {
    @Resource
    IboxService boxService;

    /**
     * 获取箱子详情列表
     */

    @GetMapping("/list")
    public TableDataInfo getBoxInfoList(TBoxInfo tBoxInfo) {

        startPage();
        return getDataTable(boxService.getBoxStandardList(StatCode.BoxInfo, tBoxInfo));
    }

    /**
     * 创建箱子信息
     */

    @PostMapping("/create")
    public AjaxResult insertBoxInfo(@RequestBody TBoxInfo tBoxInfo) {

        return AjaxResult.success(boxService.insertBoxInfo(tBoxInfo));
    }

    /**
     * 删除箱子信息
     */

    @DeleteMapping("delete/{ids}")
    public AjaxResult delelteBoxInfo(@PathVariable("ids") List<Long> ids) {


        return toAjax(boxService.delete(StatCode.BoxInfo, ids));
    }


}
