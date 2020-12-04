package com.ruoyi.project.storage.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Customer
 *
 * @author 马龙飞
 * @date 2020/11/24 10:52
 * <p>
 * 版本        修改时间        作者        修改内容
 * V1.0        2020/11/24     马龙飞        初始版本
 */
@Data
@ApiModel(value = "客户类", description = "客户类")
public class CustomerEntity extends BaseEntity {


    private static final long serialVersionUID = 3440854022584647596L;
    /**
     * 客户id
     */
    @ApiModelProperty(value = "客户ID")
    private Long userId;
    /**
     * 部门id
     */
    @ApiModelProperty(value = "部门ID")
    private Long deptId;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;
    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称")
    private String nickName;
    /**
     * 用户类型
     */
    @ApiModelProperty(value = "用户类型")
    private String userType;
    /**
     * 用户邮箱
     */
    @ApiModelProperty(value = "用户邮箱")
    private String email;
    /**
     * 用户手机号
     */
    @ApiModelProperty(value = "用户手机号")
    private String phonenumber;
    /**
     * 用户性别
     */
    @ApiModelProperty(value = "性别")
    private String sex;
    /**
     * 头像地址
     */
    @ApiModelProperty(value = "头像地址")
    private String avatar;
    /**
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码")
    private String password;
    /**
     * 用户状态
     */
    @ApiModelProperty(value = "用户状态")
    private String status;
    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除")
    private String delFlag;
    /**
     * 登录IP
     */
    @ApiModelProperty(value = "登录IP")
    private String loginIp;
    /**
     * 登录日期
     */
    @ApiModelProperty(value = "登录日期")
    private Date loginDate;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createBy;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private String updateBy;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 当前积分
     */
    @ApiModelProperty(value = "当前积分")
    private Long currentPoints;
    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号")
    private Long version;


}
