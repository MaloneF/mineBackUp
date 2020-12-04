package com.ruoyi.project.storage.service;

import com.ruoyi.framework.web.domain.BaseEntity;
import com.ruoyi.project.common.enums.TerminalEnum;
import com.ruoyi.project.storage.domain.BoxStandardEntity;
import com.ruoyi.project.storage.domain.TBoxInfo;

import java.util.List;

/**
 * IboxServic
 *
 * @author 马龙飞
 * @date 2020/11/29 11:03
 * <p>
 * 版本        修改时间        作者        修改内容
 * V1.0        2020/11/29     马龙飞        初始版本
 */
public interface IboxService {
    <T> List<T> getBoxStandardList(String code, T t);

    int createBoxStandard(String code, BoxStandardEntity boxStandardEntity);

    <T> List<T> selectStandard();

    int delete(String code, List<Long> ids);


    int insertBoxInfo(TBoxInfo tBoxInfo);

    int updateBoxStandard(BoxStandardEntity boxStandardEntity);

    BoxStandardEntity selectBoxStandardByBoxStandard(BoxStandardEntity boxStandardEntity);

}
