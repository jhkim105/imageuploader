package com.rsupport.studio.imageuploader;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest
class SystemPropertiesConfigTest {

  @Autowired
  Environment env;

  @Test
  void test() {
    assertEquals(env.getProperty("myapp.storage-path"), System.getProperty(SystemPropertiesConfig.STORAGE_PATH));
  }
}
