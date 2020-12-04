package com.ruoyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 *
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class RuoYiApplication
{
    public static void main(String[] args)
    {
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("                                             不要踏入静谧的良夜\n" +
                "                                            暮年也应在黄昏中燃烧\n" +
                "                                         反抗吧，在这将逝的时光里反抗吧\n" +
                "                                            智者临终前深知黑夜到来\n" +
                "                                           他们的智言将不能再照亮岔路          \n" +
                "                                             不要踏入静谧的良夜         \n" +
                "                                  善良的人啊，当最后一波浪潮呼啸而过，尽情哭喊吧。"
               );
    }
}
