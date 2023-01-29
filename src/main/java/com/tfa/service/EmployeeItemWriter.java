package com.tfa.service;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.tfa.entity.Employee;

@Component
public class EmployeeItemWriter extends FlatFileItemWriter<Employee> {

	public EmployeeItemWriter(@Value("data/output.csv") String outputFile) {
		setResource(new FileSystemResource(outputFile));
		setLineAggregator(getDelimitedLineAggregator());
	}

	public DelimitedLineAggregator<Employee> getDelimitedLineAggregator() {
		BeanWrapperFieldExtractor<Employee> beanWrapperFieldExtractor = new BeanWrapperFieldExtractor<Employee>();
		beanWrapperFieldExtractor.setNames(new String[] {"id", "name", "salary"});

		DelimitedLineAggregator<Employee> delimitedLineAggregator = new DelimitedLineAggregator<Employee>();
		delimitedLineAggregator.setDelimiter(";");
		delimitedLineAggregator.setFieldExtractor(beanWrapperFieldExtractor);
		return delimitedLineAggregator;
		
	}
}
