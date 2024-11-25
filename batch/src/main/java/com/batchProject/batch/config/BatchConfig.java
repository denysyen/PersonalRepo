package com.batchProject.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.batchProject.batch.respository.StudentRepository;
import com.batchProject.batch.student.Student;
import com.batchProject.batch.student.StudentProcessor;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final StudentRepository studentRepository; 
    /*
     * Implements the part of ItemReader
     */
    @Bean
    public FlatFileItemReader<Student> itemReader() {
        FlatFileItemReader<Student> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("src/main/resources/students.csv"));
        itemReader.setName("csvReader");
        itemReader.setLinesToSkip(1); // to reader after the header 
        itemReader.setLineMapper(lineMapper());

        return itemReader;
    }
    /*
     * Utility method for the itemreader part
     */
    private LineMapper<Student> lineMapper() {
        DefaultLineMapper<Student> lineMapper =  new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(","); // data separator
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id", "firstname", "lastname","age");

        BeanWrapperFieldSetMapper<Student> fieldSetMapper = new BeanWrapperFieldSetMapper<>(); // to map an Student-type object out of the data in file
        fieldSetMapper.setTargetType(Student.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;

    }
    /*
     * Implements the bean of the itemProcessor part
     */

    @Bean
    public StudentProcessor processor() {
        return new StudentProcessor();
    }

    /*
     * Implements the part of the itemWritter
     */
    @Bean
    public RepositoryItemWriter<Student> write() {
        RepositoryItemWriter<Student> repositoryItemWriter = new RepositoryItemWriter<>();
        repositoryItemWriter.setRepository(studentRepository);
        repositoryItemWriter.setMethodName("save");

        return repositoryItemWriter;
    }

    /*
     * Defining the STEPS 
     */
    @Bean
    public Step importStep() {
        return new StepBuilder("cvsImport", jobRepository)
        .<Student, Student>chunk(10, platformTransactionManager)
        .reader(itemReader())
        .processor(processor())
        .writer(write())
        .build();
    }

    @Bean
    public Job runJob() {
        return new JobBuilder("importStudents", jobRepository)
         .start(importStep())
         // here we can use the method next() to pass more steps
         .build();
    }


}
