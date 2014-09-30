package com.base.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


/**
 * 随机生成工具类
 */
public class RandomUtils extends org.apache.commons.lang.math.RandomUtils{


    /**
     * 随机字符串数生成方法
     * @param length
     * @return
     */
    public static String getRandomStr(int length) {
        String[] beforeShuffle = new String[]{"0", "1", "2", "3", "4", "5", "6", "7",
                "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
                "w", "x", "y", "z"};
        List<String> list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(list.get(i));
        }
        return sb.toString();
    }

    /**
     * 随机产生数字类型的字符串
     * @param length 字符串长度
     * @return
     */
    public static String getRandomNumber(int length) {
        String[] randomValues = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        StringBuffer vcodeString = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            vcodeString.append(randomValues[random.nextInt(10)]);
        }
        return vcodeString.toString();
    }
}