package com.ruoyi.project.storage.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * DateHandleUtil
 *
 * @author 马龙飞
 * @date 2020/11/29 12:19
 * <p>
 * 版本        修改时间        作者        修改内容
 * V1.0        2020/11/29     马龙飞        初始版本
 */

public class DataHandleUtil {

    /**
     * 占比计算保留小数的位数方法
     * 转成百分数
     * 当前数除以总数
     * @param  num1 ,num2  num1/num2
     * @return  rate  保留2位小数的
     */
public static String division(Long num1,Long num2){

    String format = String.format("%.2f", ((num1.doubleValue() / num2.doubleValue()) * 100))+"%";
return  format;
}

public static String toCode() {
    String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
//    Random r = new Random();
//   int  num = r.nextInt(900000)+100000;
    return date ;

}

}

