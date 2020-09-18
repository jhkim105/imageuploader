package com.rsupport.studio.imageuploader;

import java.io.File;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

public class ImageMagick {

  public static void resize(String sourcePath, String targetPath) {
    makeDir(targetPath);
    orient(sourcePath, sourcePath);
    String convertCommnad = getConvertCommand();
    CommandUtil.run(convertCommnad, "-quality", "65%", sourcePath, targetPath);
  }

  private static void makeDir(String targetPath) {
    String dir = FilenameUtils.getFullPathNoEndSeparator(targetPath);
    File dirPath = new File(dir);
    if (!dirPath.exists()) {
      boolean made = dirPath.mkdirs();
      if (!made) {
        throw new RuntimeException(String.format("make directory(%s) fail", dir));
      }
    }
  }

  public static void resize(String sourcePath, String targetPath, int maxWidth, int maxHeight) {
    makeDir(targetPath);
    orient(sourcePath, sourcePath);
    String convertCommnad = getConvertCommand();
    if (StringUtils.endsWith(sourcePath, ".jpg")) {
      CommandUtil.run(convertCommnad, "-resize", maxWidth + "x" + maxHeight, "-quality", "65%", sourcePath, targetPath);
    } else {
      CommandUtil.run(convertCommnad, "-resize", maxWidth + "x" + maxHeight, sourcePath, targetPath);
    }
  }

  private static String getConvertCommand() {
    return String.format("%s/convert", System.getProperty(SystemPropertiesConfig.IMAGEMAGICK_PATH));
  }



  private static void orient(String sourcePath, String targetPath) {
    String convertCommnad = getConvertCommand();
    CommandUtil.run(convertCommnad, sourcePath, "-auto-orient", targetPath);
  }
}
