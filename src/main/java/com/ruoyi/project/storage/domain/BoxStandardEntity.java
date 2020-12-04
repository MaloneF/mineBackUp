package com.ruoyi.project.storage.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

/**
 * BoxStandard
 *
 * @author 马龙飞
 * @date 2020/11/29 10:50
 * <p>
 * 版本        修改时间        作者        修改内容
 * V1.0        2020/11/29     马龙飞        初始版本
 */
@Data
@ApiModel(value = "箱子标准类", description = "箱子标准类")
public class BoxStandardEntity extends BaseEntity {

    private static final long serialVersionUID = -8955981954451527756L;
    private Long id;
    private String boxStandard;
    private Long boxUnitPrice;
    private String remark;
    private Long sortNo;
    private Date createTime;
    private String createBy;
    private Date updateTime;
    private String updateBy;
    private Long version;
    private String delFlag;
    private  Long totalNumber;
    private  Long usedNumber;
    private Long inventoryNumber;
    private String useRatio;

}
