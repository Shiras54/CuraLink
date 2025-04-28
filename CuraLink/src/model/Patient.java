package model;

import java.io.Serializable;

public class Patient implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String email;
    private String password;
    private String name;
    private String dob;
    private String contact;
    private String address;

    public Patient(String username, String email, String password, String name, String dob, String contact, String address) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.dob = dob;
        this.contact = contact;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }
    
    public String getEmail() {
    	return email;
    }
    
    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getContact() {
        return contact;
    }

    public String getAddress() {
        return address;
    }
}
