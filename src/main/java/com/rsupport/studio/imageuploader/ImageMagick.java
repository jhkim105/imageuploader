package com.rsupport.studio.imageuploader;

import org.apache.commons.lang3.StringUtils;

public class ImageMagick {

  public static void resize(String sourcePath, String targetPath, int maxWidth, int maxHeight) {
    String imageMagicToolPath = getImageMagickToolPath();
    orient(sourcePath, sourcePath);
    String convertCommnad = imageMagicToolPath + getRunFile();
    if (StringUtils.endsWith(sourcePath, ".jpg")) {
      CommandUtil.run(convertCommnad, "-resize", maxWidth + "x" + maxHeight, "-quality", "65%", sourcePath, targetPath);
    } else {
      CommandUtil.run(convertCommnad, "-resize", maxWidth + "x" + maxHeight, sourcePath, targetPath);
    }
  }

  private static String getRunFile(){
    String osName = System.getProperty("os.name");
    if (StringUtils.contains(osName.toLowerCase(), "windows")) {
      return "/magick.exe";
    } else {
      return "/convert";
    }
  }

  private static String getImageMagickToolPath() {
    return System.getProperty(SystemPropertiesConfig.IMAGEMAGICK_PATH);
  }

  private static void orient(String sourcePath, String targetPath) {
    String imageMagicToolPath = getImageMagickToolPath();

    String convertCommnad = imageMagicToolPath + getRunFile();
    CommandUtil.run(convertCommnad, sourcePath, "-auto-orient", targetPath);
  }
}
