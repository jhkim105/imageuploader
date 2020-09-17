package com.rsupport.studio.imageuploader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class CommandUtil {
  public static String run(String... command) {
    String result = "";
    Process p = null;
    try {
      if (log.isDebugEnabled()) {
        List<String> commandList = new ArrayList<>();
        for(String cmd : command) {
          commandList.add(cmd);
        }
        log.debug("command:{}", commandList);
      }
      ProcessBuilder processBuilder = new ProcessBuilder(command);
      processBuilder.redirectErrorStream(true);
      p = processBuilder.start();
      BufferedReader buf = new BufferedReader(new InputStreamReader(p.getInputStream()));
      result = IOUtils.toString(buf);
    } catch (IOException ex) {
      log.debug("{}", ex);
      throw new RuntimeException(ex);
    } finally {
      if (p != null)
        p.destroy();
    }
    if (log.isDebugEnabled())
      log.debug("result:" + result);

    return StringUtils.removeEnd(result, System.lineSeparator());
  }

}
