package com.tfa;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tfa.entity.Employee;
import com.tfa.service.EmployeeItemReader;
import com.tfa.service.EmployeeItemWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private EmployeeItemReader employeeItemReader;
	@Autowired
	private EmployeeItemWriter employeeItemWriter;

	@Bean
	public Job createJob() {
		return jobBuilderFactory.get("Job")
				.incrementer(new RunIdIncrementer())
				.flow(createStep())
				.end().build();
	}

	@Bean
	public Step createStep() {
		return stepBuilderFactory.get("My-Step")
			.<Employee,Employee>chunk(1)
			.reader(employeeItemReader)
			.writer(employeeItemWriter).build();
	}
}
