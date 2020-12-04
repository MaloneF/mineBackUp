package com.ruoyi.project.storage.utils;

import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.project.storage.domain.BoxStandardEntity;
import com.ruoyi.project.storage.mapper.BoxStandMapper;
import com.ruoyi.project.storage.mapper.TBoxInfoMapper;
import com.ruoyi.project.storage.service.IboxService;
import com.ruoyi.project.system.service.ISysUserService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * BoxCheckUtil
 *
 * @author 马龙飞
 * @date 2020/11/30 13:53
 * <p>
 * 版本        修改时间        作者        修改内容
 * V1.0        2020/11/30     马龙飞        初始版本
 */
@Component
@Slf4j
public class BoxUtil {


    private static IboxService iboxService;
    @Setter
    private static IboxService boxServiceUtil;

    @Autowired
    public BoxUtil(IboxService iboxService) {
        this.iboxService = iboxService;
    }

    @PostConstruct
    public void initBoxCheckUtil() {

        setBoxServiceUtil(iboxService);
    }

    /**
     * 检查箱子标准是否存在
     *
     * @param boxStandardEntity
     * @return boolean
     */


    public static int checkBoxStandUniqe(BoxStandardEntity boxStandardEntity) {

        List<BoxStandardEntity> list = iboxService.selectStandard();
        if (list.size() == 0) {
            //数据库中没有信息
            return 1;
        } else {
            if (null != boxStandardEntity) {

                for (BoxStandardEntity boxStandardEntity1 : list) {


                    if (boxStandardEntity1.getBoxStandard().equals(boxStandardEntity.getBoxStandard())) {
                        if (boxStandardEntity1.getBoxUnitPrice().equals(boxStandardEntity.getBoxUnitPrice())) {
                            if (boxStandardEntity.getInventoryNumber() == 0) {
                                throw new CustomException("已有库存【" + boxStandardEntity1.getInventoryNumber() + "】，您输入的值不合法");
                            } else {
                                //增加库存
                                return 2;
                            }


                        } else {
                            throw new CustomException("当前规格【" + boxStandardEntity1.getBoxStandard() + "】已存在，对应所需积分为【" + boxStandardEntity1.getBoxUnitPrice() + "】,请重新填写");


                        }
                    } else {
                        //新增数据
                        return 1;
                    }


                }

            } else {

                throw new CustomException("传入的数据为空！", HttpStatus.BAD_METHOD);


            }
        }


        throw new CustomException("未知的错误！，请联系管理员！", HttpStatus.ERROR);

    }

    public static int updateBoxStandard(int Code, BoxStandardEntity boxStandardEntity) {

        BoxStandardEntity boxStandardEntity1 = iboxService.selectBoxStandardByBoxStandard(boxStandardEntity);
        if (StatCode.Decrease == 1) {


            boxStandardEntity1.setUsedNumber(boxStandardEntity1.getUsedNumber() - 1);
            boxStandardEntity1.setInventoryNumber(boxStandardEntity1.getInventoryNumber() + 1);
            boxStandardEntity1.setUseRatio(DataHandleUtil.division(boxStandardEntity1.getUsedNumber(), boxStandardEntity1.getTotalNumber()));
            int i = iboxService.updateBoxStandard(boxStandardEntity1);

            return i;


        }
        throw new CustomException("StatCode错误", HttpStatus.BAD_METHOD);
    }


}