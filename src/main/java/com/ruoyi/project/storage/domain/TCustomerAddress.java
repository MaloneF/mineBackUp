package com.ruoyi.project.storage.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户地址表(TCustomerAddress)实体类
 *
 * @author makejava
 * @since 2020-12-02 10:35:33
 */
@Data
@ApiModel(value = "客户地址类", description = "客户地址类")
public class TCustomerAddress extends BaseEntity {
    private static final long serialVersionUID = -68161839126223270L;
    /**
     * id
     */
    private Long id;
    /**
     * 手机端用户id
     */
    private Long userId;
    /**
     * 联系人
     */
    private String contacts;
    /**
     * 联系人电话
     */
    private String contactsPhone;
    /**
     * 省编号
     */
    private String province;
    /**
     * 省名称
     */
    private String provinceName;
    /**
     * 市编号
     */
    private String city;
    /**
     * 市名称
     */
    private String cityName;
    /**
     * 区编号
     */
    private String area;
    /**
     * 区名称
     */
    private String areaName;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 是否是默认地址（0：是；1：否）
     */
    private Long isDefault;
    /**
     * 序号
     */
    private Long sortNo;
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



}