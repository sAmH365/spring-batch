package com.example.demo;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class TestConfig {

  private final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

  @PostConstruct
  public void checkBeans() {
    String[] jobBeans = context.getBeanDefinitionNames();
    for (String bean : jobBeans) {
      System.out.println("Job bean name: " + bean);
    }
  }

}
