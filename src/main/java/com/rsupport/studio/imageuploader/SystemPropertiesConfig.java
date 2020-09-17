package com.rsupport.studio.imageuploader;

import java.util.Properties;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class SystemPropertiesConfig {

  public static final String IMAGEMAGICK_PATH = "IMAGEMAGICK_PATH";
  public static final String STORAGE_PATH = "STORAGE_PATH";

  @Bean
  public MethodInvokingFactoryBean systemPropetiesBean(Environment env) {
    MethodInvokingFactoryBean bean = new MethodInvokingFactoryBean();
    bean.setTargetObject(System.getProperties());
    bean.setTargetMethod("putAll");

    Properties props = new Properties();
    props.setProperty(STORAGE_PATH, env.getProperty("myapp.storage-path"));
    props.setProperty(IMAGEMAGICK_PATH, env.getProperty("myapp.imagemagick-path"));
    bean.setArguments(new Object[] { props });
    return bean;
  }
}
