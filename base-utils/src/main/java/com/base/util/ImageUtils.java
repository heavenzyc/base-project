package com.base.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.log4j.Logger;

/**
 * description
 * 
 * @version 1.0,2011-4-14
 */
public class ImageUtils {

    private static final float MAX_SCAL = 10;
    private static Logger logger = Logger.getLogger(ImageType.class);
    /**
     * 图片类型
     * 
     */
    public static enum ImageType {
        FAX("fax"), GIF("gif"), ICO("x-icon"), JPG("jpeg,jpg,pjpeg"),

        TIF("tiff"), PNG("png"), BMP("bmp");

        /**
         * contentType中的类型，如果有多个则用“,”分隔
         */
        private final String httpContentSubtype;

        private ImageType(String httpContentSubtype) {
            this.httpContentSubtype = httpContentSubtype;
        }

        public String getHttpContentSubtype() {
            return httpContentSubtype;
        }

        public static ImageType getByContentSubtype(String httpContentSubtype) {
            httpContentSubtype = httpContentSubtype.toLowerCase().trim();
            for (ImageType item : values()) {
                for (String subyType : item.httpContentSubtype.split(",")) {
                    if (subyType.equalsIgnoreCase(httpContentSubtype))
                        return item;
                }
            }
            return null;
        }
    }

    /**
     * 位置
     * 
     */
    public static enum Position {
        /**
         * 居中
         */
        CENTER, LEFT, LEFT_TOP, TOP, RIGHT_TOP, RIGHT, RIGHT_BOTTOM, BOTTOM, LEFT_BOTTOM;
    }

    /**
     * 按照比例缩放图片
     * 
     * @param srcImageName
     *            原始图片文件
     * @param descImageName
     *            目前图片文件(请不加加后缀名,后缀名将自动加上)
     * @param scale
     *            压缩比例 20>sale>0
     */
    public static void zoom(String srcImageName, String descImageName, float scale, ImageType imageType) {
        BufferedImage srcImage = createBufferedImage(srcImageName);
        zoom(srcImage, descImageName, scale, imageType);
    }

    /**
     * 按照比例缩放图片<br/>
     * 该方法不负责关闭输入流，请外部调用者自行关闭
     * 
     * @param imageInput
     * @param descImageName
     * @param scale
     * @param imageType
     * @author xu.jianguo
     */
    public static void zoom(InputStream imageInput, String descImageName, float scale, ImageType imageType) {
        BufferedImage srcImage = createBufferedImage(imageInput);
        zoom(srcImage, descImageName, scale, imageType);
    }

    /**
     * 按照比例缩放图片
     * 
     * @param srcImage
     * @param descImageName
     * @param scale
     * @param imageType
     * @author xu.jianguo
     */
    public static void zoom(BufferedImage srcImage, String descImageName, float scale, ImageType imageType) {
        checkParam(scale);
        BufferedImage outImage = scale(srcImage, scale);
        writeImage(outImage, descImageName, imageType);
    }

    /**
     * 根据高和宽缩放图片
     * 
     * @param srcImageName
     * @param descImageName
     * @param width
     * @param height
     * @param rate
     * @param imageType
     * @author xu.jianguo
     */
    public static void zoom(String srcImageName, String descImageName, int width, int height, boolean rate,
            ImageType imageType) {
        BufferedImage srcImage = createBufferedImage(srcImageName);
        zoom(srcImage, descImageName, width, height, rate, imageType);
    }

    /**
     * 根据高和宽缩放图片<br/>
     * 该方法不负责关闭输入流，请外部调用者自行关闭
     * 
     * @param imageInput
     * @param descImageName
     * @param width
     * @param height
     * @param rate
     * @param imageType
     * @author xu.jianguo
     */
    public static void zoom(InputStream imageInput, String descImageName, int width, int height, boolean rate,
            ImageType imageType) {
        BufferedImage srcImage = createBufferedImage(imageInput);
        zoom(srcImage, descImageName, width, height, rate, imageType);
    }

    /**
     * <p>
     * 根据width和height计算缩放的比率,
     * </p>
     * <p>
     * 如果rate=false,表示非等比例缩放：
     * </p>
     * 
     * @param srcImage
     *            原始图片文件
     * @param descImageName
     *            目前图片文件(请不加加后缀名,后缀名将自动加上)
     * @param width
     *            压缩的宽度
     * @param height
     *            压缩的高度
     * @param rate
     *            是否是按比率缩放(true：按比率缩放，按实际的width和height来缩放)
     */
    public static void zoom(BufferedImage srcImage, String descImageName, int width, int height, boolean rate,
            ImageType imageType) {
        checkParam(width, height);
        BufferedImage outImage = null;
        if (rate) {
            float scale = calScale(srcImage, width, height);
            outImage = scale(srcImage, scale);
        } else {
            outImage = scale(srcImage, width, height);
        }
        writeImage(outImage, descImageName, imageType);
    }

    /**
     *      按比例缩放图片
     *      </p>
     *      <p>
     *      如果width和height的缩放比率不同则用背景色填充
     *      </p>
     * @param srcImageName
     *            原始图片文件
     * @param descImageName
     *            目前图片文件(请不加加后缀名,后缀名将自动加上)
     * @param width
     *            压缩的宽度
     * @param height
     *            压缩的高度
     * @param backGroundColor
     *            填充的背景色
     */
    public static void zoomAndFillBack(String srcImageName, String descImageName, int width, int height,
            Color backGroundColor, Position topImgPosition, ImageType imageType) {
        BufferedImage srcImage = createBufferedImage(srcImageName);
        zoomAndFillBack(srcImage, descImageName, width, height, backGroundColor, topImgPosition, imageType);
    }

    /**
     * 图片缩放并填充背景色<br/>
     * 该方法不负责关闭输入流，请外部调用者自行关闭
     * 
     * @param imageInput
     *            输入流
     * @param descImageName
     * @param width
     * @param height
     * @param backGroundColor
     * @param topImgPosition
     * @param imageType
     * @author xu.jianguo
     */
    public static void zoomAndFillBack(InputStream imageInput, String descImageName, int width, int height,
            Color backGroundColor, Position topImgPosition, ImageType imageType) {
        BufferedImage srcImage = createBufferedImage(imageInput);
        zoomAndFillBack(srcImage, descImageName, width, height, backGroundColor, topImgPosition, imageType);
    }

    public static void zoomAndFillBack(BufferedImage srcImage, String descImageName, int width, int height,
            Color backGroundColor, Position topImgPosition, ImageType imageType) {
        checkParam(width, height);
        float scale = calScale(srcImage, width, height);
        BufferedImage outImage = scale(srcImage, scale);
        if (isNeedFillBack(srcImage, width, height)) {
            outImage = fillBackGroup(outImage, width, height, backGroundColor, topImgPosition);
        }
        writeImage(outImage, descImageName, imageType);
    }

    /**
     * 填充背景色
     * 
     * @param topImage
     * @param backWidth
     * @param backHeight
     * @param backGroundColor
     * @param topImgPosition
     * @return
     * @author xu.jianguo
     */
    public static BufferedImage fillBackGroup(BufferedImage topImage, int backWidth, int backHeight,
            Color backGroundColor, Position topImgPosition) {
        int topWidth = topImage.getWidth();
        int topHeight = topImage.getHeight();
        backWidth = getBackGroundWidth(topWidth, backWidth);
        backHeight = getBackGroundHeight(topHeight, backHeight);
        int[] topImageArray = new int[topWidth * topHeight];
        topImageArray = topImage.getRGB(0, 0, topWidth, topHeight, topImageArray, 0, topWidth);
        BufferedImage backgroundImage = createBackGroundImage(backWidth, backHeight, backGroundColor);
        int[] backGroundImageArray = new int[backWidth * backHeight];
        backGroundImageArray = backgroundImage.getRGB(0, 0, backWidth, backHeight, backGroundImageArray, 0, backWidth);
        int topX = getXPosition(topWidth, backWidth, topImgPosition);
        int topY = getYPosition(topHeight, backHeight, topImgPosition);
        backgroundImage.setRGB(topX, topY, topWidth, topHeight, topImageArray, 0, topWidth);
        return backgroundImage;
    }

    private static int getXPosition(int topWidth, int backWidth, Position topImgPosition) {
        if (topImgPosition == Position.LEFT || topImgPosition == Position.LEFT_TOP
                || topImgPosition == Position.LEFT_BOTTOM) {
            return 0;
        }
        if (topImgPosition == Position.CENTER || topImgPosition == Position.TOP || topImgPosition == Position.BOTTOM) {
            return (int) ((float) backWidth / 2 - (float) topWidth / 2);
        }
        return backWidth - topWidth;
    }

    private static int getYPosition(int topHeight, int backHeight, Position topImgPosition) {
        if (topImgPosition == Position.LEFT_TOP || topImgPosition == Position.TOP
                || topImgPosition == Position.RIGHT_TOP) {
            return 0;
        }
        if (topImgPosition == Position.CENTER || topImgPosition == Position.LEFT || topImgPosition == Position.RIGHT) {
            return (int) ((float) backHeight / 2 - (float) topHeight / 2);
        }
        return backHeight - topHeight;
    }

    private static int getBackGroundWidth(int topWidth, int backWidth) {
        return backWidth > topWidth ? backWidth : topWidth;
    }

    private static int getBackGroundHeight(int topHeight, int backHeight) {
        return backHeight > topHeight ? backHeight : topHeight;
    }

    /**
     * 判断是否需要背景填充
     * 
     * @param srcImage
     * @param width
     * @param height
     * @return
     */
    private static boolean isNeedFillBack(BufferedImage srcImage, int width, int height) {
        int srcWidth = srcImage.getWidth();
        int srcHeight = srcImage.getHeight();
        float widthScale = (float) width / srcWidth;
        float heightScal = (float) height / srcHeight;
        return widthScale == heightScal ? false : true;
    }

    /**
     * 创建一个指定宽度、高度、背景色的图片
     * 
     * @param width
     * @param height
     * @param backGroundColor
     * @return
     */
    private static BufferedImage createBackGroundImage(int width, int height, Color backGroundColor) {
        BufferedImage bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bimage.createGraphics();
        g.setBackground(backGroundColor);
        g.clearRect(0, 0, width, height);
        g.dispose();
        return bimage;
    }

    private static void checkParam(int width, int height) {
        if (width > 9999 || width <= 0) {
            throw new RuntimeException("width must between 0 and 99999");
        }
        if (height > 9999 || height <= 0) {
            throw new RuntimeException("height must between 0 and 99999");
        }
    }

    /**
     * 缩放规则: 1、如果 图像宽的缩放比率为放大；则以放大率高的为最终比率 2、如果图像的宽为缩小而高为放大；则以宽的缩小比率为最终比率
     * 3、如果图像的宽和高都为缩小；则以缩小率高的最终比率
     * 
     * @param srcImage
     * @param width
     * @param height
     * @return
     */
    private static float calScale(BufferedImage srcImage, int width, int height) {
        int srcWidth = srcImage.getWidth();
        int srcHeight = srcImage.getHeight();
        float widthScale = (float) width / srcWidth;
        float heightScal = (float) height / srcHeight;
        if (width < srcWidth || height < srcHeight) {
            return widthScale > heightScal ? heightScal : widthScale;
        }
        if (widthScale >= 1 && heightScal >= 1) {
            return widthScale > heightScal ? heightScal : widthScale;
        }
        return widthScale * srcWidth >= width ? heightScal : widthScale;
    }

    private static void writeImage(BufferedImage outImage, String descImageName, ImageType imageType) {
        try {
            ImageIO.write(outImage, imageType.name(), new File(descImageName));
        } catch (IOException e) {
            throw new RuntimeException("write desc image error.", e);
        }
    }

    /**
     * 按比例缩放图片
     * 
     * @param srcImage
     * @return
     * @author xu.jianguo
     */
    public static BufferedImage scale(BufferedImage srcImage, float scale) {
        int width = (int) (srcImage.getWidth() * scale);
        int height = (int) (srcImage.getHeight() * scale);
        return scale(srcImage, width, height);
    }

    /**
     * 缩放图片
     * 
     * @param srcImage
     * @param width
     * @param height
     * @return
     * @author xu.jianguo
     */
    public static BufferedImage scale(BufferedImage srcImage, int width, int height) {
        Image image = srcImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage bimage = new BufferedImage(width, height, srcImage.getType());
        Graphics g = bimage.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return bimage;

    }

    /**
     * 参数检测
     * 
     * @param scale
     * @author xu.jianguo
     */
    private static void checkParam(float scale) {
        if (scale <= 0) {
            throw new RuntimeException("scal not less than 0");
        }
        if (scale > MAX_SCAL) {
            throw new RuntimeException("scal not more than " + MAX_SCAL);
        }
    }

    private static BufferedImage createBufferedImage(String srcImageName) {
        try {
            return ImageIO.read(new File(srcImageName));
        } catch (IOException e) {
            throw new RuntimeException("read src image error.", e);
        }
    }

    private static BufferedImage createBufferedImage(InputStream ins) {
        try {
            return ImageIO.read(ins);
        } catch (IOException e) {
            throw new RuntimeException("read src image error.", e);
        }
    }

    /**
     * 图片剪切，图片的是以左上角为原点
     * 
     * @param srcImageName
     *            原图
     * @param descImageName
     *            剪切保存后的目标图
     * @param leftTopPoint
     *            左上角位置
     * @param rightBottomPoint
     *            右下角位置
     * @author xu.jianguo
     */
    public static void cut(String srcImageName, String descImageName, Point leftTopPoint, Point rightBottomPoint,
            ImageType imageType) {
        BufferedImage srcImage = createBufferedImage(srcImageName);
        cut(srcImage, descImageName, leftTopPoint, rightBottomPoint, imageType);
    }

    public static void cut(InputStream ins, String descImageName, Point leftTopPoint, Point rightBottomPoint,
            ImageType imageType) {
        BufferedImage srcImage = createBufferedImage(ins);
        cut(srcImage, descImageName, leftTopPoint, rightBottomPoint, imageType);
    }

    public static void cut(BufferedImage srcImage, String descImageName, Point leftTopPoint, Point rightBottomPoint,
            ImageType imageType) {
        if (imageType == null)
            imageType = ImageType.JPG;
        BufferedImage outImage = cut(srcImage, leftTopPoint, rightBottomPoint);
        writeImage(outImage, descImageName, imageType);

    }

    /**
     * 图片剪切<br/>
     * 返回剪切后的图像
     * 
     * @param image
     * @param leftTopPoint
     * @param rightBottomPoint
     * @return
     * @author xu.jianguo
     */
    public static BufferedImage cut(BufferedImage image, Point leftTopPoint, Point rightBottomPoint) {
        leftTopPoint.reCalLeftTopPoint(image);
        int width = calCutWidth(image.getWidth(), leftTopPoint, rightBottomPoint.x - leftTopPoint.x);
        int height = calCutHeight(image.getHeight(), leftTopPoint, rightBottomPoint.y - leftTopPoint.y);
        return cutImage(image, leftTopPoint, width, height);
    }

    /**
     * 图片剪切，图片的是以左上角为原点
     * 
     * @param srcImageName
     * @param descImageName
     * @param leftTopPoint
     * @param width
     * @param height
     * @author xu.jianguo
     */
    public static void cut(String srcImageName, String descImageName, Point leftTopPoint, int width, int height,
            ImageType imageType) {
        BufferedImage srcImage = createBufferedImage(srcImageName);
        cut(srcImage, descImageName, leftTopPoint, width, height, imageType);
    }

    public static void cut(InputStream ins, String descImageName, Point leftTopPoint, int width, int height,
            ImageType imageType) {
        BufferedImage srcImage = createBufferedImage(ins);
        cut(srcImage, descImageName, leftTopPoint, width, height, imageType);
    }

    public static void cut(BufferedImage srcImage, String descImageName, Point leftTopPoint, int width, int height,
            ImageType imageType) {
        if (imageType == null)
            imageType = ImageType.JPG;
        BufferedImage outImage = cut(srcImage, leftTopPoint, width, height);
        writeImage(outImage, descImageName, imageType);
    }

    /**
     * 图片剪切<br/>
     * 返回剪切后的图像
     * 
     * @param image
     * @param leftTopPoint
     * @param width
     * @param height
     * @return
     * @author xu.jianguo
     */
    public static BufferedImage cut(BufferedImage image, Point leftTopPoint, int width, int height) {
        leftTopPoint.reCalLeftTopPoint(image);
        width = calCutWidth(image.getWidth(), leftTopPoint, width);
        height = calCutHeight(image.getHeight(), leftTopPoint, height);
        return cutImage(image, leftTopPoint, width, height);
    }

    private static BufferedImage cutImage(BufferedImage srcImage, Point leftTopPoint, int width, int height) {
        BufferedImage outImage = new BufferedImage(width, height, srcImage.getType());
        Graphics g = outImage.getGraphics();
        g.drawImage(srcImage.getSubimage(leftTopPoint.x, leftTopPoint.y, width, height), 0, 0, null);
        g.dispose();
        return outImage;

    }

    private static int calCutWidth(int imageWidth, Point leftTopPoint, int width) {
        int maxWidth = imageWidth - leftTopPoint.x;
        return (width < 0 || width > maxWidth) ? maxWidth : width;
    }

    private static int calCutHeight(int imageHeight, Point leftTopPoint, int height) {
        int maxHeight = imageHeight - leftTopPoint.y;
        return (height < 0 || height > maxHeight) ? maxHeight : height;
    }

    public static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }

        /**
         * 从新计算x和y坐标，保证坐标的正确
         * 
         * @param image
         * @author xu.jianguo
         */
        private void reCalLeftTopPoint(BufferedImage image) {
            x = (x < 0 || x >= image.getWidth()) ? 0 : x;
            y = (y < 0 || y >= image.getHeight()) ? 0 : y;
        }
    }

    /**
     * 获取图片的真实类型
     * 
     * @param filePath
     *            图片的绝对路径
     * @return
     */
    public final static ImageType getImageRealType(String filePath) {
        String typeString = null;
        String filetypeHex = String.valueOf(getFileHeader(filePath));
        Iterator<Map.Entry<String, String>> entryiterator = FILE_TYPE_MAP.entrySet().iterator();
        while (entryiterator.hasNext()) {
            Map.Entry<String, String> entry = entryiterator.next();
            String fileTypeHexValue = entry.getKey();
            if (filetypeHex.toUpperCase().startsWith(fileTypeHexValue)) {
                typeString = entry.getValue();
            }
        }
        return typeString == null ? null : ImageType.getByContentSubtype(typeString);
    }

    /**
     * 图片文件头信息，和对应的图片类型对应关系
     */
    public static final HashMap<String, String> FILE_TYPE_MAP = new HashMap<String, String>();
    static {
        FILE_TYPE_MAP.put("FFD8FF", "jpg");
        FILE_TYPE_MAP.put("89504E47", "png");
        FILE_TYPE_MAP.put("47494638", "gif");
        FILE_TYPE_MAP.put("49492A00", "tif");
        FILE_TYPE_MAP.put("424D", "bmp");
    }

    /**
     * 获取文件头信息，返回十六进制字符串
     * 
     * @param filePath
     * @return
     */
    private static String getFileHeader(String filePath) {
        FileInputStream is = null;
        String value = null;
        try {
            is = new FileInputStream(filePath);
            byte[] b = new byte[4];
            is.read(b, 0, b.length);
            value = bytesToHexString(b);
        } catch (Exception e) {
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
        return value;
    }

    /**
     * 将字节数组转换为十六进制
     * 
     * @param src
     * @return
     */
    private static String bytesToHexString(byte[] src) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        String hv;
        for (int i = 0; i < src.length; i++) {
            // 以十六进制(基数 16)无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
            hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        return builder.toString();
    }

    /*
     * 根据尺寸图片居中裁剪
     */
    public static void cutCenterImage(String src, String dest, int w, int h) throws IOException {
        Iterator iterator = ImageIO.getImageReadersByFormatName("jpg");
        ImageReader reader = (ImageReader) iterator.next();
        InputStream in = new FileInputStream(src);
        ImageInputStream iis = ImageIO.createImageInputStream(in);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        int imageIndex = 0;
        Rectangle rect = new Rectangle((reader.getWidth(imageIndex) - w) / 2, (reader.getHeight(imageIndex) - h) / 2,
                w, h);
        param.setSourceRegion(rect);
        BufferedImage bi = reader.read(0, param);
        ImageIO.write(bi, "jpg", new File(dest));

    }

    /*
     * 图片裁剪二分之一
     */
    public static void cutHalfImage(String src, String dest) throws IOException {
        Iterator iterator = ImageIO.getImageReadersByFormatName("jpg");
        ImageReader reader = (ImageReader) iterator.next();
        InputStream in = new FileInputStream(src);
        ImageInputStream iis = ImageIO.createImageInputStream(in);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        int imageIndex = 0;
        int width = reader.getWidth(imageIndex) / 2;
        int height = reader.getHeight(imageIndex) / 2;
        Rectangle rect = new Rectangle(width / 2, height / 2, width, height);
        param.setSourceRegion(rect);
        BufferedImage bi = reader.read(0, param);
        ImageIO.write(bi, "jpg", new File(dest));
    }

    /**
     * 图像切割
     * 
     * @param srcImageFile
     *            源图像地址
     * @param dirImageFile
     *            新图像地址
     * @param x
     *            目标切片起点x坐标
     * @param y
     *            目标切片起点y坐标
     * @param destWidth
     *            目标切片宽度
     * @param destHeight
     *            目标切片高度
     */
    public static File cut(String srcImageFile, String dirImageFile, int x, int y, int destWidth, int destHeight) {
        try {
            Image img;
            ImageFilter cropFilter;
            File outFile = null;
            // 读取源图像
            BufferedImage bi = ImageIO.read(new File(srcImageFile));
            int srcWidth = bi.getWidth(); // 源图宽度
            int srcHeight = bi.getHeight(); // 源图高度

            if (srcWidth >= destWidth && srcHeight >= destHeight) {
                Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
                // 改进的想法:是否可用多线程加快切割速度
                // 四个参数分别为图像起点坐标和宽高
                // 即: CropImageFilter(int x,int y,int width,int height)
                cropFilter = new CropImageFilter(x, y, destWidth, destHeight);
                img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
                BufferedImage tag = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
                Graphics g = tag.getGraphics();
                g.drawImage(img, 0, 0, null); // 绘制缩小后的图
                g.dispose();
                // 输出为文件
                outFile = new File(dirImageFile);
                ImageIO.write(tag, "JPEG", outFile);

            }
            return outFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 图像切割（改） *
     * 
     * @param srcImageFile
     *            源图像地址
     * @param dirImageFile
     *            新图像地址
     * @param x
     *            目标切片起点x坐标
     * @param y
     *            目标切片起点y坐标
     * @param destWidth
     *            目标切片宽度
     * @param destHeight
     *            目标切片高度
     */
    public static File abscut(String srcImageFile, String dirImageFile, int x, int y, int w, int h, int destWidth,
            int destHeight, int jcropWight, int jcropHeight) {

        try {
            Image img;
            ImageFilter cropFilter;
            File outFile = null;
            // 读取源图像
            BufferedImage bi = ImageIO.read(new File(srcImageFile));
            int srcWidth = bi.getWidth(); // 源图宽度
            int srcHeight = bi.getHeight(); // 源图高度
            if (srcWidth >= w && srcHeight >= h) {
                logger.debug("需要进行裁剪:" + " srcWidth=" + srcWidth + " w=" + w + " srcHeight=" + srcHeight + " h="
                        + h);
                Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
                // TODO
                // 改进的想法:是否可用多线程加快切割速度
                // 四个参数分别为图像起点坐标和宽高
                // 即: CropImageFilter(int x,int y,int width,int height)
                cropFilter = new CropImageFilter(x, y, w, h);
                img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
                BufferedImage tag = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                Graphics g = tag.getGraphics();
                g.drawImage(img, 0, 0, null); // 绘制缩小后的图
                g.dispose();
                // 输出为文件
                outFile = new File(dirImageFile);
                ImageIO.write(tag, "JPEG", outFile);
                BufferedImage bo = ImageIO.read(outFile);
                logger.debug("getWidth=" + bo.getWidth());
                logger.debug("getHeight=" + bo.getHeight());

                // scale(dirImageFile, dirImageFile, destWidth, destHeight);

            } else {
                logger.debug("不需要进行裁剪:" + " srcWidth=" + srcWidth + " w=" + w + " srcHeight=" + srcHeight + " h="
                        + srcHeight);
                outFile = new File(dirImageFile);
                FileUtils.copyFile(new File(srcImageFile), outFile);
            }
            return outFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 缩放图像
     * 
     * @param srcImageFile
     *            源图像文件地址
     * @param result
     *            缩放后的图像地址
     * @param scale
     *            缩放比例
     * @param flag
     *            缩放选择:true 放大; false 缩小;
     */
    public static void scale(String srcImageFile, String result, int scale, boolean flag) {
        try {
            BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件
            int width = src.getWidth(); // 得到源图宽
            int height = src.getHeight(); // 得到源图长
            if (flag) {
                // 放大
                width = width * scale;
                height = height * scale;
            } else {
                // 缩小
                width = width / scale;
                height = height / scale;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            ImageIO.write(tag, "JPEG", new File(result));// 输出到文件流
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重新生成按指定宽度和高度的图像
     * 
     * @param srcImageFile
     *            源图像文件地址
     * @param result
     *            新的图像地址
     * @param _width
     *            设置新的图像宽度
     * @param _height
     *            设置新的图像高度
     */
    public static void scale(String srcImageFile, String result, int _width, int _height) {
        scale(srcImageFile, result, _width, _height, 0, 0);
    }

    public static void scale(String srcImageFile, String result, int _width, int _height, int x, int y) {
        try {

            BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件

            int width = src.getWidth(); // 得到源图宽
            int height = src.getHeight(); // 得到源图长
            if (width > _width) {
                width = _width;
            }
            if (height > _height) {
                height = _height;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, x, y, null); // 绘制缩小后的图
            g.dispose();
            ImageIO.write(tag, "JPEG", new File(result));// 输出到文件流
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 图像类型转换 GIF->JPG GIF->PNG PNG->JPG PNG->GIF(X)
     */
    public static void convert(String source, String result) {
        try {
            File f = new File(source);
            f.canRead();
            f.canWrite();
            BufferedImage src = ImageIO.read(f);
            ImageIO.write(src, "JPG", new File(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 彩色转为黑白
     * 
     * @param source
     * @param result
     */
    public static void gray(String source, String result) {
        try {
            BufferedImage src = ImageIO.read(new File(source));
            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
            ColorConvertOp op = new ColorConvertOp(cs, null);
            src = op.filter(src, null);
            ImageIO.write(src, "JPEG", new File(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文字水印
     * 
     * @param pressText
     * @param targetImg
     */
    public static void pressText(String pressText, String targetImg) {
        try {
            File img = new File(targetImg);
            Image src = ImageIO.read(img);
            int width = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.drawImage(src, 0, 0, width, height, null);
            g.setColor(Color.black);
            g.setFont(new Font("黑体", 10, 20));
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.5f));
            g.drawString(pressText, 10, 20);
            g.dispose();
            ImageIO.write((BufferedImage) image, "jpg", img);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
