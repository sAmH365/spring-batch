package com.example.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DBJobConfiguration {

  @Bean
  public Job jobName(JobRepository jobRepository, Step step1, Step step2) {
    return new JobBuilder("jobName", jobRepository)
        .incrementer(new RunIdIncrementer()) // 항상 새로운 ID 부여
        .start(step1)
        .next(step2)
        .build();
  }

  @Bean
  public Step step1(JobRepository jobRepository, PlatformTransactionManager tx) {
    return new StepBuilder("step1", jobRepository)
        .tasklet((contribution, chunkContext) -> {
          System.out.println("=====================");
          System.out.println(">> my step1");
          System.out.println("=====================");

          return RepeatStatus.FINISHED;
        }, tx).build();
  }

  @Bean
  public Step step2(JobRepository jobRepository, PlatformTransactionManager tx) {
    return new StepBuilder("step2", jobRepository)
        .tasklet((contribution, chunkContext) -> {
          System.out.println("=====================");
          System.out.println(">> my step2");
          System.out.println("=====================");


          return RepeatStatus.FINISHED;
        }, tx).build();
  }

}
