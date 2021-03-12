package com.rsupport.studio.imageuploader;

import com.rsupport.studio.imageuploader.UploadDto.UploadResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@RequestMapping("/upload")
@RestController
public class UploadController {

  @PostMapping
  public ResponseEntity<UploadResponse> upload(MultipartHttpServletRequest multipartHttpServletRequest) {
    MultipartFile multipartFile = multipartHttpServletRequest.getFile("file");
    if (multipartFile.isEmpty())
      return ResponseEntity.notFound().build();

    String uploadDir = String.format("%s/original/%s",
        System.getProperty(SystemPropertiesConfig.STORAGE_PATH),
        LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
    File uploadedFile = FileUtils.uploadWithUniqueName(multipartFile, uploadDir);

    String imageUri = String.format("%s/%s",
      LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")),
      uploadedFile.getName());

    String imagePath = String.format("%s/images/%s", System.getProperty(SystemPropertiesConfig.STORAGE_PATH), imageUri);

    ImageMagick.resize(uploadedFile.getAbsolutePath(), imagePath);
    String url = String.format("%s/%s",
      MvcUriComponentsBuilder.fromController(this.getClass())
          .build().toUri(), imageUri);

    return ResponseEntity.ok(UploadResponse.of(url));
  }

  @GetMapping(value = {"/**/*.jpg", "/**/*.jpeg"}, produces = MediaType.IMAGE_JPEG_VALUE)
  public byte[] downloadJpg(HttpServletRequest request) {
    return getImage(request);
  }

  @GetMapping(value = "/**/*.png", produces = MediaType.IMAGE_PNG_VALUE)
  public byte[] downloadPng(HttpServletRequest request) {
    return getImage(request);
  }

  @GetMapping(value = "/**/*.gif", produces = MediaType.IMAGE_GIF_VALUE)
  public byte[] downloadGif(HttpServletRequest request) {
    return getImage(request);
  }


  private byte[] getImage(HttpServletRequest request) {
    int index = request.getRequestURI().indexOf("upload");
    String imageUri = request.getRequestURI().substring(index + "upload".length() + 1);
    String imagePath = String.format("%s/images/%s", System.getProperty(SystemPropertiesConfig.STORAGE_PATH), imageUri);
    try {
      return Files.readAllBytes(Paths.get(imagePath));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }




}
