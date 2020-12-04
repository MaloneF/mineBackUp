package com.ruoyi.project.storage.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单表(TOrder)实体类
 *
 * @author makejava
 * @since 2020-12-02 14:50:29
 */
@Data
@ApiModel(value = "订单类", description = "订单类")
public class TOrder extends BaseEntity {
    private static final long serialVersionUID = 733250584305607444L;
    /**
     * id
     */
    private Long id;
    /**
     * 手机端用户id
     */
    private Long userId;
    /**
     * 订单状态（手机端——1：已下上门单；2：待收空箱；3：已收空箱；4：已发重箱；5：已存储；6：已下提取单；7：待收重箱；8：已收重箱；9：已发空箱；10：已完成）订单状态（后台端——1：待发空箱；2：已发空箱；3：送达空箱；4：待发重箱；5：已存储；6：待发重箱；7：已发重箱；8：送达重箱；9：待收空箱；10：已完成）
     */
    private Long status;
    /**
     * 订单编号（年月日+6位序列）
     */
    private String orderCode;
    /**
     * 订单名称
     */
    private String orderName;
    /**
     * 空箱上门下单时间（订单状态为1-5，显示）
     */
    private Date emptyBoxOrderTime;
    /**
     * 重箱提取下单时间（订单状态为6-10，显示）
     */
    private Date heavyBoxOrderTime;
    /**
     * 空箱上门姓名（订单状态为1-5，显示）
     */
    private String emptyBoxCallName;
    /**
     * 空箱上门电话（订单状态为1-5，显示）
     */
    private String emptyBoxCallPhone;
    /**
     * 空箱上门地址（订单状态为1-5，显示）
     */
    private String emptyBoxCallAddress;
    /**
     * 空箱上门时间（年月日，订单状态为1-5，显示）
     */
    private String emptyBoxCallTime;
    /**
     * 空箱上门时段（如09:00-13:00，订单状态为1-5，显示）
     */
    private String emptyBoxCallInterval;
    /**
     * 重箱提取姓名（订单状态为6-10，显示）
     */
    private String heavyBoxCallName;
    /**
     * 重箱提取电话（订单状态为6-10，显示）
     */
    private String heavyBoxCallPhone;
    /**
     * 重箱提取地址（订单状态为6-10，显示）
     */
    private String heavyBoxCallAddress;
    /**
     * 重箱提取时间（年月日，订单状态为6-10，显示）
     */
    private String heavyBoxCallTime;
    /**
     * 重箱提取时段（如09:00-13:00，订单状态为6-10，显示）
     */
    private String heavyBoxCallInterval;
    /**
     * 箱子id
     */
    private Long boxId;
    /**
     * 箱子编号（年月日+6位序列）
     */
    private String boxCode;
    /**
     * 箱子规格（如20*20*20）
     */
    private String boxStandard;
    /**
     * 箱子积分单价（每月积分单价）
     */
    private Long boxUnitPrice;
    /**
     * 租赁时长（月）
     */
    private Long leaseDuration;
    /**
     * 实付积分
     */
    private     Long totalPrice;
    /**
     * 序号
     */
    private       Long sortNo;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 版本号
     */
    private Long version;
    /**
     * 删除标记（0：未删除；2：已删除）
     */
    private String delFlag;
    /**
     * 手机端删除标记（0：未删除；2：已删除）
     */
    private String appDelFlag;
    /**
     * 后台端删除标记（0：未删除；2：已删除）
     */
    private String backendDelFlag;
    /**
     * 用户名
     */



    private String nickName;
}