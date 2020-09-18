package com.rsupport.studio.imageuploader;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Slf4j
class ImageMagickTest {

  @Test
  @Disabled
  void resize() throws Exception {
    String storagePath = System.getProperty(SystemPropertiesConfig.STORAGE_PATH);
    ImageMagick.resize(storagePath +"/test.jpg", storagePath + "/out.jpg", 2000,2000);
    ImageMagick.resize(storagePath +"/test.jpg", storagePath + "/out2.jpg");

  }

}
