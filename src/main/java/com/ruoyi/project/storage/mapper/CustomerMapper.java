package com.ruoyi.project.storage.mapper;

import com.ruoyi.project.storage.domain.CustomerEntity;

import java.util.List;
import java.util.Map;

/**
 * CustomerMapper
 *
 * @author 马龙飞
 * @date 2020/11/25 10:36
 * <p>
 * 版本        修改时间        作者        修改内容
 * V1.0        2020/11/25     马龙飞        初始版本
 */
public interface CustomerMapper {
    List<CustomerEntity> selectCustomer(CustomerEntity customerEntity);

    int insertCustomer(CustomerEntity customerEntity);

    int updateCustomer(CustomerEntity customerEntity);

    int logicdeleteCustomer(Long id);

    int resect(Map<String, Object> batchResetMapByUserIds);

    int operateCustomer(Map<String, Object> batchUpdateMapByOperateUserIds);

    String getPassword(Long userId);

    int updatePassword(CustomerEntity customerEntity);

    CustomerEntity selectCustomerById(CustomerEntity customerEntity);

}
