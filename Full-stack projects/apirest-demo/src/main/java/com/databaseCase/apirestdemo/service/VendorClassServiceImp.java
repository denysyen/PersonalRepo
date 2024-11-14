package com.databaseCase.apirestdemo.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.databaseCase.apirestdemo.exceptionhandler.VendorClassNotFoundExceptions;
import com.databaseCase.apirestdemo.model.VendorClass;
import com.databaseCase.apirestdemo.repository.VendorClassRepository;

@Service
public class VendorClassServiceImp implements VendorClassService  {

    VendorClassRepository vendorClassRepository;

    public VendorClassServiceImp(VendorClassRepository vendorClassRepository) {
        this.vendorClassRepository = vendorClassRepository;
    }
    

    @Override
    public String createVendorClass(VendorClass vendorClass) {
        vendorClassRepository.save(vendorClass);
        return "vendor saved successfully";
    }

    @Override
    public String updateVendorClass(VendorClass vendorClass) {
        vendorClassRepository.save(vendorClass);
        return "vendor updated successfully";
    }


    @Override
    public String deleteVendorClass(String vendorId) {
        vendorClassRepository.deleteById(vendorId);
        return "vendor deleted successfully";
    }


    @Override
    public VendorClass getVendorClass(String vendorId) {
        if(vendorClassRepository.findById(vendorId).isEmpty()) {
            throw new VendorClassNotFoundExceptions("Request vendor not found");
        }
        return vendorClassRepository.findById(vendorId).get();
    }


    @Override
    public List<VendorClass> getAllVendorClass() {

        return vendorClassRepository.findAll();
    }
}
