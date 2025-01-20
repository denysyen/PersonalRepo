package com.databaseCase.apirestdemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.databaseCase.apirestdemo.model.VendorClass;
import com.databaseCase.apirestdemo.response.ResponseHandler;
import com.databaseCase.apirestdemo.service.VendorClassService;
import java.util.*;
@RestController
@RequestMapping("/vendorClass")
public class VendorClassController {

    VendorClassService vendorClassService;

    public VendorClassController(VendorClassService vendorClassService) {
        this.vendorClassService = vendorClassService;
    }
    @GetMapping("{vendorId}")
    public ResponseEntity<Object> getClassvendorInformation(@PathVariable("vendorId") String vendorId) {
        
        return ResponseHandler.responseBuilder( "Requested vendor found", HttpStatus.OK, vendorClassService.getVendorClass(vendorId));
    }

    @GetMapping()
    public List<VendorClass> getAllClassVendors () {
        return vendorClassService.getAllVendorClass();
    }

    @PostMapping()
    public String updateClassVendorInformation (@RequestBody VendorClass classVendor) {

        vendorClassService.createVendorClass(classVendor);
        return "The vendorClass information was updated";
    }

    @DeleteMapping
    public String deleteClassVendorInformation (@RequestBody VendorClass classVendor) {
        return "The vendorClass information was deleted";
    }

}
