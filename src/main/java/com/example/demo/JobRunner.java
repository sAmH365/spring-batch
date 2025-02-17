package com.example.demo;

import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JobRunner implements ApplicationRunner {

  private final JobLauncher jobLauncher;

  private final Job jobParamJob;

  @Override
  public void run(ApplicationArguments args) throws Exception {

//    JobParameters jobParameters = new JobParametersBuilder().addString("name2", "user2")
//        .toJobParameters();

    // customer job param
    JobParameters jobParameters = new JobParametersBuilder()
        .addString("name", "user1")
        .addLong("seq", 2L)
        .addDate("date", new Date())
        .addDouble("age", 0.5)
        .toJobParameters();

    jobLauncher.run(jobParamJob, jobParameters);
  }
}
