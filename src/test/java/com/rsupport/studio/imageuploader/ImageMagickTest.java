package com.rsupport.studio.imageuploader;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;


@SpringBootTest
@Slf4j
class ImageMagickTest {

  @Test
  void resize() throws Exception {
    String storagePath = System.getProperty(SystemPropertiesConfig.STORAGE_PATH);
    ImageMagick.resize(storagePath +"/test.png", storagePath + "/out.png", 2000,2000);

  }

}
