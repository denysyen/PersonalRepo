package com.example;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateUserDetailsRequesr {
    
    @NotNull(message="firstName cannot be Null")
    @Size(min=2, message ="firstName must not be less than 2 characters")   
    private String firstName;

    @NotNull(message="lastName cannot be Null")
    @Size(min=2, message="lastName must not be less than 2 characters")
    private String lastName;
    

    /**
     * @return String return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return String return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
