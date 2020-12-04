package com.ruoyi.project.storage.service.impl;

import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.project.common.util.SeqGeneratorUtil;
import com.ruoyi.project.storage.domain.BoxStandardEntity;
import com.ruoyi.project.storage.domain.TBoxInfo;
import com.ruoyi.project.storage.mapper.BoxStandMapper;
import com.ruoyi.project.storage.mapper.TBoxInfoMapper;
import com.ruoyi.project.storage.service.IboxService;

import com.ruoyi.project.storage.utils.BoxUtil;
import com.ruoyi.project.storage.utils.DataHandleUtil;
import com.ruoyi.project.storage.utils.ParameterUtil;
import com.ruoyi.project.storage.utils.StatCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * BoxServiceImple
 *
 * @author 马龙飞
 * @date 2020/11/29 11:04
 * <p>
 * 版本        修改时间        作者        修改内容
 * V1.0        2020/11/29     马龙飞        初始版本
 */
@Service
@Slf4j
public class BoxServiceImple implements IboxService {
    @Resource
    BoxStandMapper boxStandMapper;
    @Resource
    TBoxInfoMapper boxInfoMapper;

    /**
     * 删除箱子记录
     *
     * @param code 操作码，ids 需要删除的id列表
     * @return 实例对象
     */
    @Override
    public int delete(String code, List<Long> ids) {
        //对code进行判断
        if (code.equals("箱子信息")) {
            //通过id查询出箱子信息列表
//            List<TBoxInfo> list = boxInfoMapper.queryByIds(ids);
//            List<String> standardList = new ArrayList<>();
//            //将箱子信息中的箱子标准放入新的列表
//            for (TBoxInfo tBoxInfo : list) {
//
//                standardList.add(tBoxInfo.getBoxStandard());
//            }
//            //更新箱子标准表
//            boxStandMapper.decreaseBoxStandard(standardList);
            return boxInfoMapper.deleteById(ids);


        } else if (code.equals("箱子规格")) {


            List<BoxStandardEntity> list = boxStandMapper.selectBoxStandardById(ids);
            List<String> list1 = new ArrayList<>();

            for (BoxStandardEntity boxStandardEntity : list) {


                list1.add(boxStandardEntity.getBoxStandard());


            }

            boxInfoMapper.deleteByBoxStandard(list1);

            return boxStandMapper.delete(ids);

        }

        return 0;

    }

    /**
     * 更新箱子标准
     *
     * @param code 操作码，boxStandardEntity 需要创建的实体类
     * @return 更新的字段数量
     */
    @Override
    public int createBoxStandard(String code, BoxStandardEntity boxStandardEntity) {
        if (BoxUtil.checkBoxStandUniqe(boxStandardEntity) == 1) {


            ParameterUtil.setCreateEntity(boxStandardEntity);
//            boxStandardEntity.setTotalNumber(boxStandardEntity.getInventoryNumber());
//            boxStandardEntity.setUseRatio("0%");
//            boxStandardEntity.setUsedNumber(0L);
            List<TBoxInfo> entities = new ArrayList<>();

            for (int i = 0; i < boxStandardEntity.getInventoryNumber(); i++) {
                TBoxInfo tBoxInfo = new TBoxInfo();
                BeanUtils.copyBeanProp(tBoxInfo, boxStandardEntity);
                ParameterUtil.setCreateEntity(tBoxInfo);
                tBoxInfo.setBoxCode(SeqGeneratorUtil.seqGenerator(DataHandleUtil.toCode(), 6));
                tBoxInfo.setUsed("0");
                tBoxInfo.setUsedByName(SecurityUtils.getUserId());
                entities.add(tBoxInfo);
            }
            boxInfoMapper.insertBatch(entities);

            return boxStandMapper.insert(boxStandardEntity);

        } else {
            ParameterUtil.setCreateEntity(boxStandardEntity);
//            boxStandardEntity.setTotalNumber(boxStandardEntity.getInventoryNumber());
//            boxStandardEntity.setUseRatio("0%");
//            boxStandardEntity.setUsedNumber(0L);
            List<TBoxInfo> entities = new ArrayList<>();

            for (int i = 0; i < boxStandardEntity.getInventoryNumber(); i++) {
                TBoxInfo tBoxInfo = new TBoxInfo();
                BeanUtils.copyBeanProp(tBoxInfo, boxStandardEntity);
                ParameterUtil.setCreateEntity(tBoxInfo);
                tBoxInfo.setBoxCode(SeqGeneratorUtil.seqGenerator(DataHandleUtil.toCode(), 6));
                tBoxInfo.setUsed("0");
                tBoxInfo.setUsedByName(SecurityUtils.getUserId());
                entities.add(tBoxInfo);
            }
            int i = boxInfoMapper.insertBatch(entities);
            return i;
        }


    }

    /**
     * 查询箱子标准
     *
     * @return 箱子标准列表
     */
    @Override
    public List<BoxStandardEntity> selectStandard() {


        return boxStandMapper.selectStandard();
    }

    /**
     * 插入箱子信息
     *
     * @param tBoxInfo 箱子信息实体类
     * @return 更新字段数量
     */
    @Override
    public int insertBoxInfo(TBoxInfo tBoxInfo) {

        ParameterUtil.setCreateEntity(tBoxInfo);
        tBoxInfo.setBoxCode(SeqGeneratorUtil.seqGenerator(DataHandleUtil.toCode(), 6));
        tBoxInfo.setUsed("0");
        tBoxInfo.setUsedByName(SecurityUtils.getUserId());
        BoxStandardEntity boxStandardEntity = new BoxStandardEntity();
        boxStandardEntity.setBoxStandard(tBoxInfo.getBoxStandard());
        //调用工具类
//        BoxUtil.updateBoxStandard(StatCode.Increase, boxStandardEntity);
        return boxInfoMapper.insert(tBoxInfo);
    }

    /**
     * 根据箱子实体类查询箱子标准
     *
     * @param boxStandardEntity 箱子标准实体类
     * @return 箱子标准实体类
     */
    @Override
    public BoxStandardEntity selectBoxStandardByBoxStandard(BoxStandardEntity boxStandardEntity) {
        return boxStandMapper.selectBoxStandardByBoxStandard(boxStandardEntity.getBoxStandard());
    }

    /**
     * 查询箱子列表
     *
     * @param t 泛型
     * @return 查询结果
     */
    @Override
    public <T> List<T> getBoxStandardList(String code, T t) {
        if (null != code && code.equals("箱子信息")) {

            return boxInfoMapper.queryAll(t);

        } else if (code.equals("箱子规格")) {


            return boxStandMapper.selectBoxStandard(t);

        }

        throw new CustomException("输入信息非法", HttpStatus.ERROR);
    }

    /**
     * 查询箱子标准
     *
     * @param boxStandardEntity 箱子标准实体类
     * @return 生效的行数
     */

    @Override
    public int updateBoxStandard(BoxStandardEntity boxStandardEntity) {
        return boxStandMapper.increaseBoxStandard(boxStandardEntity);

    }


}