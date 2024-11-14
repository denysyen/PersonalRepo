package com.databaseCase.apirestdemo.controllerTest;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.databaseCase.apirestdemo.controller.VendorClassController;
import com.databaseCase.apirestdemo.model.VendorClass;
import com.databaseCase.apirestdemo.service.VendorClassService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.when;

import java.util.*;
@WebMvcTest(VendorClassController.class)
public class VendorClassControllerTest {
    // I want to test the request via URL 
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VendorClassService vendorClassService;
    VendorClass vendorClassOne;
    VendorClass vendorClassTwo;
    List<VendorClass> vendorClassList =  new ArrayList<>();

    @Test
    void testName() {
        
    }

    @BeforeEach
    void setUp() {
        vendorClassOne = new VendorClass("1", "One", "Address 1", "XXXXX");
        vendorClassTwo = new VendorClass("2", "Two", "Address 2", "XXXXY");

        vendorClassList.add(vendorClassOne);
        vendorClassList.add(vendorClassTwo);
    }
    @AfterEach
    void tearDown() {
        
    }

    @Test
    void testGetClassvendorInformation() throws Exception {
         ResultMatcher ok = MockMvcResultMatchers.status().isOk();

        when(vendorClassService.getVendorClass("1")).thenReturn(vendorClassOne);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/vendorClass/1")).andDo(MockMvcResultHandlers.print()).andExpect(ok);

    }

    @Test 
    void testUpdateClassVendorInformation() throws Exception{
        ResultMatcher ok = MockMvcResultMatchers.status().isOk();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);

        ObjectWriter wr = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = wr.writeValueAsString(vendorClassOne);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
			.post("/vendorClass")
			.contentType(MediaType.APPLICATION_JSON)
            .content(requestJson);

        when(vendorClassService.createVendorClass(vendorClassOne)).thenReturn("vendor updated successfully");

        this.mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print()).andExpect(ok);
    }

    

}
