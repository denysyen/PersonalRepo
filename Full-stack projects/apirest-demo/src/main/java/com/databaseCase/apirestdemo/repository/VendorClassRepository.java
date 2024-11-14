package com.databaseCase.apirestdemo.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import com.databaseCase.apirestdemo.model.VendorClass;


public interface VendorClassRepository extends JpaRepository<VendorClass, String> {
     
    List<VendorClass> findByVendorName(String vendorName);
}
