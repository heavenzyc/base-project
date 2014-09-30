package com.base.util;

import java.util.Random;

public class NumberUtils extends org.apache.commons.lang.math.NumberUtils{

    /**
     * 随机产生数字
     *
     * @return
     */
    static public Integer getRandomInteger() {
        return (new Double(Math.random() * 10000).intValue());
    }

    public static Integer getRandomNum(Integer r){
        Random random = new Random();
        return Math.abs(random.nextInt())%r;
    }
}
