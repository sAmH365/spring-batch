package com.example.demo;


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BatchJobRunner {

  private final JobLauncher jobLauncher;
  private final Job jobName;

//  @PostConstruct
  public void runJob() {
    try {
      JobParameters jobParameters = new JobParametersBuilder()
          .addLong("time", System.currentTimeMillis()) // 항상 새로운 파라미터 추가
          .toJobParameters();

      jobLauncher.run(jobName, jobParameters);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
