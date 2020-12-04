package com.ruoyi.project.storage.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.enums.TerminalEnum;
import com.ruoyi.project.storage.domain.BoxStandardEntity;
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
 * @date 2020/11/29 10:56
 * <p>
 * 版本        修改时间        作者        修改内容
 * V1.0        2020/11/29     马龙飞        初始版本
 */
@RestController
@Slf4j
@RequestMapping("/backend/box/standard")
public class BackendBoxStandardController extends BaseController {
    @Resource
    private IboxService boxservice;

    @GetMapping("/list")
    public TableDataInfo getBoxStanderd(BoxStandardEntity boxStandard) {

        startPage();
        return getDataTable(boxservice.getBoxStandardList(StatCode.BoxStatus, boxStandard));

    }

    @PostMapping("/create")
    public AjaxResult create(@RequestBody BoxStandardEntity boxStandard) {

        startPage();
        return toAjax(boxservice.createBoxStandard(StatCode.BoxInfo,boxStandard));

    }

    @GetMapping("/select")

    public AjaxResult selectStandard() {


        return AjaxResult.success("操作成功", boxservice.selectStandard());

    }

    @DeleteMapping("delete/{ids}")
    public AjaxResult logicDelete(@PathVariable("ids")List<Long> ids) {
        AjaxResult ajaxResult = toAjax(boxservice.delete( StatCode.BoxStatus,ids));
        return ajaxResult;
    }

}
