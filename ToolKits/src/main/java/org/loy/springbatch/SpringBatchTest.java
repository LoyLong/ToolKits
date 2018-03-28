package org.loy.springbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

public class SpringBatchTest {

    public static void main(String[] args) throws Exception {
        String[] configLocations = {"springbatch/applicationContext.xml"};
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(configLocations);

        JobRepository jobRepository = applicationContext.getBean(JobRepository.class);
        PlatformTransactionManager transactionManager = applicationContext.getBean(PlatformTransactionManager.class);
        StepBuilderFactory stepBuilderFactory = new StepBuilderFactory(jobRepository, transactionManager);

        FlatFileItemReader<DeviceCommand> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new FileSystemResource("C:/WorkSpace/Log/springbatchtesting/batch-data_before.csv"));
        flatFileItemReader.setLineMapper(new HelloLineMapper());

        FlatFileItemWriter<DeviceCommand> flatFileItemWriter = new FlatFileItemWriter<>();
        flatFileItemWriter.setResource(new FileSystemResource("C:/WorkSpace/Log/springbatchtesting/batch-data_after.csv"));
        flatFileItemWriter.setLineAggregator(new HelloLineAggregator());

        HelloItemProcessor helloItemProcessor = new HelloItemProcessor();

        Step step = stepBuilderFactory.get("step")
                          .<DeviceCommand, DeviceCommand>chunk(1)
                          .reader(flatFileItemReader)
                          .processor(helloItemProcessor)
                          .writer(flatFileItemWriter)
                          .build();

        JobBuilderFactory jobBuilderFactory = new JobBuilderFactory(jobRepository);
        Job job = jobBuilderFactory.get("job").start(step).build();

        JobLauncher jobLauncher = applicationContext.getBean(JobLauncher.class);
        jobLauncher.run(job, new JobParameters());
    }

}
