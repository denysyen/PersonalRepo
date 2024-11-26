package com.batchProject.batch.student;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public class StudentProcessor implements ItemProcessor<Student, Student> {

    @Override
    @Nullable
    public Student process(@NonNull Student student) throws Exception {
        // normally the transformation and processing logics goes here ! 
        student.setId(null); // Hibernate will persist and create a new Id with the desire Strategy
        return student;
    }
     
}
