package com.rsupport.studio.imageuploader;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
  public static File upload(MultipartFile multipartFile, String uploadDir, String saveFileName) {
    File dirPath = new File(uploadDir);
    if (!dirPath.exists()) {
      boolean made = dirPath.mkdirs();
      if (!made) {
        throw new RuntimeException(String.format("make directory(%s) fail", uploadDir));
      }
    }

    String targetFilePath = uploadDir + "/" + saveFileName;
    File targetFile = new File(targetFilePath);
    try {
      multipartFile.transferTo(targetFile);
    } catch (IOException ex) {
      throw new RuntimeException(String.format("file upload error:%s", targetFilePath), ex);
    }
    return targetFile;
  }

  public static File uploadWithUniqueName(MultipartFile multipartFile, String uploadDir) {
    String randomString = StringUtils.remove(UUID.randomUUID().toString(), "-");
    String filename = String.format("%s.%s", randomString, FilenameUtils.getExtension(multipartFile.getOriginalFilename()));
    return upload(multipartFile, uploadDir, filename);
  }


}
