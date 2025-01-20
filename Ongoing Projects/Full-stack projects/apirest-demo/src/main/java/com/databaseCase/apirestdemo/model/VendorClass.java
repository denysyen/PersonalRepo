package com.databaseCase.apirestdemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendorClass_info")
public class VendorClass {
    
    @Id
    private String vendorId;
    private String vendorName;
    private String vendorAddress;
    private String vendorPhoneNumber;


    public VendorClass() {
    }

    public VendorClass(String vendorId, String vendorName, String vendorAddress, String vendorPhoneNumber) {
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.vendorAddress = vendorAddress;
        this.vendorPhoneNumber = vendorPhoneNumber;
    }

    /**
     * @return String return the vendorId
     */
    public String getVendorId() {
        return vendorId;
    }

    /**
     * @param vendorId the vendorId to set
     */
    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    /**
     * @return String return the vendorName
     */
    public String getVendorName() {
        return vendorName;
    }

    /**
     * @param vendorName the vendorName to set
     */
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    /**
     * @return String return the vendorAddress
     */
    public String getVendorAddress() {
        return vendorAddress;
    }

    /**
     * @param vendorAddress the vendorAddress to set
     */
    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    /**
     * @return String return the vendorPhoneNumber
     */
    public String getVendorPhoneNumber() {
        return vendorPhoneNumber;
    }

    /**
     * @param vendorPhoneNumber the vendorPhoneNumber to set
     */
    public void setVendorPhoneNumber(String vendorPhoneNumber) {
        this.vendorPhoneNumber = vendorPhoneNumber;
    }

}
