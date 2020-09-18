package com.rsupport.studio.imageuploader;

import static org.junit.jupiter.api.Assertions.*;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

@Slf4j
class RequestUtilsTest {

  @Test
  void getServerUrl() {
    MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
    String url = RequestUtils.getServerUrl(httpServletRequest);
    log.debug("{}", url);
    assertEquals(url, "http://localhost");
  }
}
