package com.rsupport.studio.imageuploader;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.partWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParts;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration.class)
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
      .andExpect(jsonPath("url").exists())
      .andDo(document("upload",
        requestParts(partWithName("file").description("file")),
        responseFields(fieldWithPath("url").description("download url"))
        )
      )
    ;
  }

  @Test
  void download() throws Exception {
    ResultActions resultActions = mockMvc.perform(
      MockMvcRequestBuilders
        .get("/upload/20200924/4a71b270e9aa4238b8c206237463216a.jpg")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.IMAGE_JPEG));

    resultActions
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.IMAGE_JPEG));

    resultActions.andDo(document("download"));
  }

}
