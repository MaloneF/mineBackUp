package com.ruoyi.project.storage.service.impl;

import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.common.enums.RecordPointWay;
import com.ruoyi.project.common.enums.TerminalEnum;
import com.ruoyi.project.storage.domain.CustomerEntity;
import com.ruoyi.project.storage.domain.PointEntity;
import com.ruoyi.project.storage.mapper.CustomerMapper;
import com.ruoyi.project.storage.mapper.PointMapper;
import com.ruoyi.project.storage.service.ICustomerService;
import com.ruoyi.project.storage.service.PointService;
import com.ruoyi.project.storage.utils.ParameterUtil;
import com.ruoyi.project.system.domain.SysUserRole;
import com.ruoyi.project.system.mapper.SysUserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * CustomerServiceImple
 *
 * @author 马龙飞
 * @date 2020/11/25 10:35
 * <p>
 * 版本        修改时间        作者        修改内容
 * V1.0        2020/11/25     马龙飞        初始版本
 */
@Service
@Slf4j
public class CustomerServiceImple implements ICustomerService {
    @Resource
    private CustomerMapper customerMapper;
    @Resource
    private PointService pointService;
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;
    @Resource
    private PointMapper mapper;

    /**
     * 更新密码
     *
     * @param terminalEnum 终端类型（APP：手机端；BACKEND：后台端；SYSTEM：系统端；REGIST：手机注册端）
     * @param oldPassWord  旧密码
     * @param newPassword  新密码
     * @return int
     */
    @Override
    public int updatePassword(TerminalEnum terminalEnum, String oldPassWord, String newPassword) {


        if (TerminalEnum.REGIST.equals(terminalEnum) || TerminalEnum.BACKEND.equals(terminalEnum)) {


            if (SecurityUtils.matchesPassword(oldPassWord, customerMapper.getPassword(SecurityUtils.getUserId()))) {


                if (SecurityUtils.encryptPassword(oldPassWord).equals(SecurityUtils.encryptPassword(newPassword))) {

                    throw new CustomException("修改密码失败，新密码不能与旧密码相同", HttpStatus.ERROR);


                } else {


                    CustomerEntity customerEntity = new CustomerEntity();
                    customerEntity.setUserId(SecurityUtils.getUserId());
                    customerEntity.setPassword(SecurityUtils.encryptPassword(newPassword));

                    return customerMapper.updatePassword(customerEntity);

                }


            } else {
                throw new CustomException("修改密码失败，旧密码错误", HttpStatus.ERROR);


            }
        } else {
            throw new CustomException("登录端错误", HttpStatus.UNAUTHORIZED);
        }
    }


    /**
     * 用户信息修改
     *
     * @param terminalEnum   终端类型（APP：手机端；BACKEND：后台端；SYSTEM：系统端；REGIST：手机注册端）
     * @param customerEntity 用户实体
     * @return int
     */
    @Override
    public int update(TerminalEnum terminalEnum, CustomerEntity customerEntity) {
        //设置更新字段
        ParameterUtil.setUpdateEntity(customerEntity);
        customerEntity.setUserType(terminalEnum.getType());
        log.error("CustomerEntity值：{}", customerEntity);
        int count = customerMapper.updateCustomer(customerEntity);
        if (0 == count) {

            log.error("修改信息失败:乐观锁");
            throw new CustomException("用户被其他人更改，请稍后再试", HttpStatus.CONFLICT);
        }
        return count;
    }

    /**
     * 用户逻辑删除
     *
     * @param terminalEnum 终端类型（APP：手机端；BACKEND：后台端；SYSTEM：系统端；REGIST：手机注册端）
     * @param IdList       需要修改的用户id列表
     * @return int
     */
    @Override
    public int delete(TerminalEnum terminalEnum, List<Long> IdList) {
        int count = 0;

        if (TerminalEnum.APP.equals(terminalEnum) || TerminalEnum.BACKEND.equals(terminalEnum)) {


            for (Long s : IdList) {

                int i = customerMapper.logicdeleteCustomer(s);
                count = count + i;

            }


        } else {

            throw new CustomException("认证失败，请检查后重试");
        }


        return count;
    }

    /**
     * 重置密码
     *
     * @param terminalEnum 终端类型（APP：手机端；BACKEND：后台端；SYSTEM：系统端；REGIST：手机注册端）
     * @param userIds      需要更新的id列表
     * @return int
     */

    @Override
    public int resetPassword(TerminalEnum terminalEnum, Long[] userIds) {
        if (TerminalEnum.APP.equals(terminalEnum) || TerminalEnum.BACKEND.equals(terminalEnum)) {
            return customerMapper.resect(ParameterUtil.getBatchResetMapByUserIds(terminalEnum, userIds));

        } else {
            throw new CustomException("登录端错误！");

        }

    }

    @Override
    /**
     * 用户注册
     *
     * @param terminalEnum 终端类型（APP：手机端；BACKEND：后台端；SYSTEM：系统端；REGIST：手机注册端）
     * @param customerEntity 用户实体
     * @return int
     */
    public int CustomerRegister(TerminalEnum terminalEnum, CustomerEntity customerEntity) {
        /*
         * 设置通用信息
         */
        //判断是否在后台手机端注册
        if (TerminalEnum.APP.equals(terminalEnum) || TerminalEnum.BACKEND.equals(terminalEnum)) {

            ParameterUtil.setCreateEntity(customerEntity);
        } else {
            //Register不需要获取当前登录管理员账户。
            ParameterUtil.setRegistEntity(customerEntity);
        }
        /*
         * 设置详细信息
         */
        customerEntity.setDeptId(terminalEnum.getDept());
        customerEntity.setUserType(terminalEnum.getType());
        customerEntity.setPassword(SecurityUtils.encryptPassword("".equals(terminalEnum.getPassword()) ? customerEntity.getPassword() : terminalEnum.getPassword()));
        customerEntity.setStatus("0");
        customerEntity.setCurrentPoints(terminalEnum.getPoint());
        log.info("调试信息{}", customerEntity.toString());
        int count = customerMapper.insertCustomer(customerEntity);
        if (0 < count) {

            boolean a = TerminalEnum.APP.equals(terminalEnum);
            boolean b = TerminalEnum.BACKEND.equals(terminalEnum);
            if (a || b) {

                count = pointService.registerCustomerPoints(RecordPointWay.REGIST, customerEntity.getUserId(), terminalEnum.getPoint(), null, null);

            } else if (TerminalEnum.REGIST.equals(terminalEnum)) {


                count = pointService.registerCustomerPoints(RecordPointWay.REGIST, customerEntity.getUserId(), terminalEnum.getPoint(), null, null);
            }
            if (0 < count) {
                //实例化List<SysUserRole>
                List<SysUserRole> list = new ArrayList<>();

                SysUserRole sysUserRole = new SysUserRole();

                sysUserRole.setUserId(customerEntity.getUserId());
                sysUserRole.setRoleId(terminalEnum.getRole());
                list.add(sysUserRole);
                log.error("用户类型{}", sysUserRole.toString());
                //插入用户角儿关联表返回条数
                return sysUserRoleMapper.batchUserRole(list);
            } else {


                throw new CustomException("插入积分表示失败");

            }
        } else {


            throw new CustomException("插入用户表失败");
        }


    }

    /**
     * 用户查询
     *
     * @param terminalEnum   终端类型（APP：手机端；BACKEND：后台端；SYSTEM：系统端；REGIST：手机注册端）
     * @param customerEntity 用户实体
     * @return List<CustomerEntity>
     */

    @Override
    public List<CustomerEntity> selectCustomer(TerminalEnum terminalEnum, CustomerEntity customerEntity) {

        customerEntity.setUserType(terminalEnum.getType());

        return customerMapper.selectCustomer(customerEntity);
    }


    /**
     * 用户启用/禁用
     *
     * @param terminalEnum 终端类型（APP：手机端；BACKEND：后台端；SYSTEM：系统端；REGIST：手机注册端）
     * @param operate      启动/禁用
     * @param userIds      用户id数组
     * @return int
     */
    @Override
    public int enableOrDisableCustomer(TerminalEnum terminalEnum, String operate, Long[] userIds) {
        if (TerminalEnum.APP.equals(terminalEnum) || TerminalEnum.BACKEND.equals(terminalEnum)) {

            return customerMapper.operateCustomer(ParameterUtil.getBatchUpdateMapByOperateUserIds(terminalEnum, operate, userIds));

        } else {

            throw new CustomException("登录端错误！");

        }


    }
}
