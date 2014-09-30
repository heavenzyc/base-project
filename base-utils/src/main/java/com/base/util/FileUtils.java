package com.base.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FileUtils extends org.apache.commons.io.FileUtils {

    public static String URL_SEPARATOR = "/";

    /**
     * 路径是否存在并且为文件夹
     * 
     * @param filePath
     * @return
     */
    public static boolean isDirectory(String filePath) {
        File file = new File(filePath);
        if (file != null && file.isDirectory()) {
            return true;
        }
        return false;
    }

    /**
     * 创建目录
     * 
     * @param filePath
     *            文件路径
     * @return String 返回类型
     * @return
     */
    public static boolean createDirectory(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return file.mkdirs();
        }
        return false;
    }

    /**
     * 创建传入时间为名字
     * 
     * @param filePath
     * @param date
     * @return 文件件路径
     */
    public static String createDirectoryByDate(String filePath, Date date) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String name = format.format(calendar.getTime());
        String path = filePath + File.separator + name;
        createDirectory(path);
        return name;
    }

    /**
     * 创建传入时间为名字的文件夹
     * 
     * @param filePath
     * @return
     */
    public static String createDirectoryByNow(String filePath) {
        return createDirectoryByDate(filePath, new Date());
    }

    /**
     * 获取文件类型
     * 
     * @param @param fileName
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public static String getFileType(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."), fileName.length());
    }

    /**
     * 检查文件类型
     * 
     * @param @param fileName
     * @param @param isImgmail.hannovertech.com
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     */
    public static boolean checkFileType(String fileName, String types) {
        String fileType = getFileType(fileName);
        return types.indexOf(fileType.toLowerCase()) == -1;
    }

}
