package com.example;


public class UserRest {
    private String firsName;
    private String lastName;
    private String email;
    private String password;

    

    /**
     * @return String return the firsName
     */
    public String getFirsName() {
        return firsName;
    }

    /**
     * @param firsName the firsName to set
     */
    public void setFirsName(String firsName) {
        this.firsName = firsName;
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

    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return String return the userdId
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param userdId the userdId to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
