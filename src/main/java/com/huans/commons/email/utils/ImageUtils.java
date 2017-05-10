package com.huans.commons.email.utils;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageUtils {
     
    /**
     * 通过判断图片的宽度和高度来确定是否是图片
     * @param imageFile
     * @return
     */ 
     public static boolean isImage(File imageFile) { 
        if (!imageFile.exists()) { 
            return false; 
        } 
        Image img = null; 
        try { 
            img = ImageIO.read(imageFile); 
            if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) { 
                return false; 
            } 
            return true; 
        } catch (Exception e) { 
            return false; 
        } finally { 
            img = null; 
        } 
    } 
 
}
