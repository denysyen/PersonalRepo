package com.batchProject.batch.student;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public class StudentProcessor implements ItemProcessor<Student, Student> {

    @Override
    @Nullable
    public Student process(@NonNull Student arg0) throws Exception {
        // normally the transformation and processing logics goes here ! 
        return arg0;
    }
     
}
