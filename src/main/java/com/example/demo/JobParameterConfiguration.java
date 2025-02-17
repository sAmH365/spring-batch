package com.example.demo;

import java.util.Map;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JobParameterConfiguration {

  @Bean(name = "jobParamJob")
  public Job jobParamJob(JobRepository jobRepository, Step jobParamStep1, Step jobParamStep2) {
    return new JobBuilder("jobParamJob", jobRepository)
        .start(jobParamStep1)
        .next(jobParamStep2)
        .build();
  }

  @Bean
  public Step jobParamStep1(JobRepository jobRepository, PlatformTransactionManager tx) {
    return new StepBuilder("jobParamStep1", jobRepository)
        .tasklet((contribution, chunkContext) -> {

          JobParameters jobParameters = contribution.getStepExecution().getJobExecution()
              .getJobParameters();
          jobParameters.getString("name");
          jobParameters.getLong("seq");
          jobParameters.getDate("date");
          jobParameters.getDouble("age");

          Map<String, Object> jobParameters1 = chunkContext.getStepContext().getJobParameters();

          System.out.println("=============================");
          System.out.println(" >>> jobParamStep1");
          System.out.println("=============================");

          return RepeatStatus.FINISHED;
        }, tx).build();
  }

  @Bean
  public Step jobParamStep2(JobRepository jobRepository, PlatformTransactionManager tx) {
    return new StepBuilder("jobParamStep2", jobRepository)
        .tasklet((contribution, chunkContext) -> {

          System.out.println("=============================");
          System.out.println(" >>> jobParamStep2");
          System.out.println("=============================");

          return RepeatStatus.FINISHED;
        }, tx).build();
  }
}
