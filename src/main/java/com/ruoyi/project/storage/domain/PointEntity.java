package com.ruoyi.project.storage.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * PointEntity
 *
 * @author 马龙飞
 * @date 2020/11/25 13:25
 * <p>
 * 版本        修改时间        作者        修改内容
 * V1.0        2020/11/25     马龙飞        初始版本
 */
@Data
@ApiModel(value = "积分标准被", description = "积分标准类")
public class PointEntity extends BaseEntity {
    private static final long serialVersionUID = -2052791242537111993L;
    /**
     * 积分表id
     */
    @ApiModelProperty(value = "积分表ID")
    private Long point_id;
    /**
     * 客户id
     */
    @ApiModelProperty(value = "客户ID")
    private Long userId;
    /**
     * 获取方式 方式（1：注册赠送；2：活动获得；3：积分使用）
     */
    @ApiModelProperty(value = "获取方式")

    private Integer way;

    /**
     * 积分（积分获得为正数，积分使用未负数）
     */
    @ApiModelProperty(value = "客户积分")
    private Long points;
    /**
     * 广告ID
     */
    @ApiModelProperty(value = "广告ID")
    private Long advertisementId;
    /**
     * 订单ID
     */
    @ApiModelProperty(value = "订单ID")
    private Long orderId;
    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private Long sortNo;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createBy;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private String updateBy;
    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号")
    private Long version;
    /**
     * 删除标记(0：未删除 2：已删除)
     */
    @ApiModelProperty(value = "广告ID")
    private String delFlag;


}
