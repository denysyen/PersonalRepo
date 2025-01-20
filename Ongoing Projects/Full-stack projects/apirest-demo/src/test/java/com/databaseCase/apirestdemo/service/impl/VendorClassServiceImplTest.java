package com.databaseCase.apirestdemo.service.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.*;
import com.databaseCase.apirestdemo.model.VendorClass;
import com.databaseCase.apirestdemo.repository.VendorClassRepository;
import com.databaseCase.apirestdemo.service.VendorClassService;
import com.databaseCase.apirestdemo.service.VendorClassServiceImp;
import java.util.*;

public class VendorClassServiceImplTest {

    @Mock
    private VendorClassRepository vendorClassRepository;
    
    private VendorClassService vendorClassService;
    
     // I want to close after test are done 
    AutoCloseable autoCloseable;  
    VendorClass vendorClass;

    @Test
    void testName() {
        
    }

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this); 
        vendorClassService = new VendorClassServiceImp(vendorClassRepository);
        vendorClass = new VendorClass("1", "One", "Address 1", "XXXXX");
        
        
    }

    @AfterEach
    void tearDown()  throws Exception {
        autoCloseable.close();
    }

    @Test
    void testCreateVendorClass() {
        mock(VendorClass.class);
        mock(VendorClassRepository.class);

        when(vendorClassRepository.save(vendorClass)).thenReturn(vendorClass);
        assertThat(vendorClassService.createVendorClass(vendorClass)).isEqualTo("vendor saved successfully");

    }

    @Test
    void testUpdateVendorClass() {
        mock(VendorClass.class);
        mock(VendorClassRepository.class);

        when(vendorClassRepository.save(vendorClass)).thenReturn(vendorClass);
        assertThat(vendorClassService.updateVendorClass(vendorClass)).isEqualTo("vendor updated successfully");

    }

    @Test
    void testGetVendorClass() {
        mock(VendorClass.class);
        mock(VendorClassRepository.class);

        when(vendorClassRepository.findById("1")).thenReturn(Optional.ofNullable(vendorClass));
        assertThat(vendorClassService.getVendorClass("1").getVendorName()).isEqualTo(vendorClass.getVendorName());

    }
    
    @Test
    void testGetAllVendorClass() {
        mock(VendorClass.class);
        mock(VendorClassRepository.class);

         // I want only one instance of vendorClass Mock for test
        when(vendorClassRepository.findAll()).thenReturn(new ArrayList<VendorClass>(Collections.singleton(vendorClass)));

        assertThat(vendorClassService.getAllVendorClass().get(0).getVendorPhoneNumber()).isEqualTo(vendorClass.getVendorPhoneNumber());
    }

}
