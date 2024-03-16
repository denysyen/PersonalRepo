package com.databaseCase.apirestdemo.repositoryTest;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.*;
import com.databaseCase.apirestdemo.model.VendorClass;
import com.databaseCase.apirestdemo.repository.VendorClassRepository;

@DataJpaTest
public class RepositoryTest {

    //  given  - when  - then 

    @Autowired
    private VendorClassRepository vendorClassRepository ;
    VendorClass vendorClass;

    @BeforeEach
    void setUp() {
        // to make sure I have some data at DB
        vendorClass =  new VendorClass("1", "One", "Address One", "XXXXXX1");
        vendorClassRepository.save(vendorClass);
    }

    @AfterEach
    void tearDown() {
        vendorClass = null;
        vendorClassRepository.deleteAll();
    }

    @Test
    void testFindByVendorClassName_Found() {
        List<VendorClass> vendorClassList = vendorClassRepository.findByVendorName("One");
        assertThat(vendorClassList.get(0).getVendorId()).isEqualTo(vendorClass.getVendorId());
        assertThat(vendorClassList.get(0).getVendorAddress()).isEqualTo(vendorClass.getVendorAddress());
    }

    @Test
    void testFindByVendorClassName_NotFound() {
        List<VendorClass> vendorClassList = vendorClassRepository.findByVendorName("TEST");
        assertThat(vendorClassList.isEmpty()).isTrue();
    }

}
