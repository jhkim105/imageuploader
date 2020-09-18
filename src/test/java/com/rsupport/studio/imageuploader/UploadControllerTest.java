package com.rsupport.studio.imageuploader;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class UploadControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void upload() throws Exception {
    String storagePath = System.getProperty(SystemPropertiesConfig.STORAGE_PATH);
    MockMultipartFile multipartFile = new MockMultipartFile( "file", "test.jpg", MediaType.IMAGE_JPEG_VALUE,
      Files.readAllBytes(Paths.get(String.format("%s/%s", storagePath, "test.jpg"))) );

    mockMvc.perform(multipart("/upload").file(multipartFile))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("url").exists());
  }

}
