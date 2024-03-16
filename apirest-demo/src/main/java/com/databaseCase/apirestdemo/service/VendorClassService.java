package com.databaseCase.apirestdemo.service;

import java.util.*;
import com.databaseCase.apirestdemo.model.VendorClass;

public interface VendorClassService {
    public String createVendorClass(VendorClass vendorClass);
    public String updateVendorClass (VendorClass vendorClass);
    public String deleteVendorClass (String vendorId);
    public VendorClass getVendorClass (String vendorId);
    public List<VendorClass> getAllVendorClass (); 
}

