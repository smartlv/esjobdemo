package com.bluestone.esjob.demo;

import com.bluestone.commons.util.ThreadUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 作者: smartlv 日期：2016/12/20.
 */
public class ProviderJob
{
    public static void main(String[] args)
    {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        ThreadUtils.sleepQuiet(Integer.MAX_VALUE);
    }
}
