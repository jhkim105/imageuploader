package com.rsupport.studio.imageuploader;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    MockMultipartFile file = new MockMultipartFile( "file", "hello.txt", MediaType.TEXT_PLAIN_VALUE,
      "Hello, World!".getBytes() );

    mockMvc.perform(multipart("/upload").file(file))
      .andExpect(status().isOk());
  }

}