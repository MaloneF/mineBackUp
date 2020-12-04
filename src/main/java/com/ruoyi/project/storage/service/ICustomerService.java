package com.ruoyi.project.storage.service;

import com.ruoyi.project.common.enums.TerminalEnum;
import com.ruoyi.project.storage.domain.CustomerEntity;
import com.ruoyi.project.storage.domain.PointEntity;

import java.util.List;

/**
 * ICustomerService
 *
 * @author 马龙飞
 * @date 2020/11/25 10:34
 * <p>
 * 版本        修改时间        作者        修改内容
 * V1.0        2020/11/25     马龙飞        初始版本
 */

public interface ICustomerService {

    public List<CustomerEntity> selectCustomer(TerminalEnum terminalEnum, CustomerEntity customerEntity);

    int CustomerRegister(TerminalEnum terminalEnum, CustomerEntity customerEntity);

    int update(TerminalEnum terminalEnum, CustomerEntity customerEntity);

    int delete(TerminalEnum terminalEnum, List<Long> IdList);

    int resetPassword(TerminalEnum terminalEnum, Long[] userIds);

    int enableOrDisableCustomer(TerminalEnum terminalEnum, String operate, Long[] userIds);

     int  updatePassword(TerminalEnum terminalEnum,String oldPassWord,String newPassword);
}
