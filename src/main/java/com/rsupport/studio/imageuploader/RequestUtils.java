package com.rsupport.studio.imageuploader;

import java.net.MalformedURLException;
import java.net.URI;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

public class RequestUtils {

//  public static String getServerUrl(HttpServletRequest request) {
//    String address = request.getServerName();
//    int port = request.getServerPort();
//    StringBuffer url = new StringBuffer();
//    String scheme = request.getScheme();
//    url.append(scheme).append("://").append(address);
//    if (!StringUtils.contains(address, ":")) {
//      if (port < 0) {
//        port = 80; // Work around java.net.URL bug
//      }
//      if ((scheme.equals("http") && (port != 80)) || (scheme.equals("https") && (port != 443))) {
//        url.append(':').append(port);
//      }
//
//    }
//    url.append(request.getContextPath());
//    return url.toString();
//  }

  public static String getServerUrl(HttpServletRequest request) {
    URI uri = ServletUriComponentsBuilder.fromRequest(request).build().toUri();
    try {
      return uri.toURL().toString();
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }
}
