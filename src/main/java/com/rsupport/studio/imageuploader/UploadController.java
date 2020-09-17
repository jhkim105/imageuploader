package com.rsupport.studio.imageuploader;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

@RequestMapping("/upload")
@RestController
public class UploadController {

  @PostMapping
  public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
    if (file.isEmpty())
      return ResponseEntity.notFound().build();


    return ResponseEntity.ok().build();
  }



}
