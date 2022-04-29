package com.invenia.gwservice;

import com.invenia.gwservice.rabbitmq.DestinationsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(DestinationsConfig.class)
public class GwServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(GwServiceApplication.class, args);
  }

}
