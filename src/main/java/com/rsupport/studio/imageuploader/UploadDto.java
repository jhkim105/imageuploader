package com.rsupport.studio.imageuploader;


import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

public class UploadDto {

  @Getter
  @ToString
  @RequiredArgsConstructor
  public static class UploadResponse implements Serializable {

    private static final long serialVersionUID = 5102911662091875185L;

    @NonNull
    private String url;

    public static UploadResponse of(String url) {
      UploadResponse uploadResponse = new UploadResponse(url);
      return uploadResponse;
    }
  }


}
