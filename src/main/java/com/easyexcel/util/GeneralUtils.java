package com.easyexcel.util;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

/**
 * @author Mr丶s
 * @date 2020/6/6 1:31 下午
 * @description 通用工具类
 */


public class GeneralUtils {


    /**
     * 去掉个别不好识别的字符
     */
    private static String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "m", "n", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
            "2", "3", "4", "5", "6", "7", "8", "9", "0"};

    /**
     * 正整数规则
     */
    private static String POSITIVE_INTEGER_PATTERN = "^-?\\d+(\\.\\d+)?$";

    /**
     * 是否为数字的正则
     */
    private static Pattern pattern = Pattern.compile(POSITIVE_INTEGER_PATTERN);


    /**
     * 随机产生一串指定位数的随机数
     *
     * @param length 指定位数长度
     * @return 随机数字符串
     */
    public static String randomNum(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("随机数长度必须大于0");
        } else if (length < 18) {
            long start = (long) Math.pow(10, length - 1);
            long end = (long) Math.pow(10, length);
            return String.valueOf(ThreadLocalRandom.current().nextLong(start, end));
        } else {
            StringBuilder sb = new StringBuilder(length);
            ThreadLocalRandom random = ThreadLocalRandom.current();
            for (int i = 0; i < length; i++) {
                sb.append(random.nextInt(10));
            }
            return sb.toString();
        }
    }

    /**
     * 随机产生一串指定位数的随机字符
     *
     * @param length 指定位数长度
     * @return 随机字符串
     */
    public static String randomChar(int length) {
        //  去掉两个不好识别的
        String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "m", "n", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
                "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(chars.length);
            result.append(chars[randomNum]);
        }
        return result.toString();
    }


    /**
     * 判断一个字符串是正整数
     *
     * @param string 字符串
     * @return 是否为数字
     */
    public static boolean isPositiveNumber(String string) {
        return StringUtils.isNotBlank(string) && pattern.matcher(string).matches();
    }




    /**
     * 根据文件路径获取文件头信息
     *
     * @return 文件头信息
     */
    public static String getFileHeader(InputStream stream) {
        String value = null;
        try {
            byte[] b = new byte[4];
            stream.read(b, 0, b.length);
            value = bytesToHexString(b);
        } catch (Exception ignored) {
        } finally {
            if (null != stream) {
                try {
                    stream.close();
                } catch (IOException ignored) {
                }
            }
        }
        return value;
    }



    /**
     * byte数组转换成16进制字符串
     *
     * @param src
     * @return
     */
    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte b : src) {
            // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
            String hv = Integer.toHexString(b & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
