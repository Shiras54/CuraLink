package model;

import java.io.Serializable;

public class Doctor implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String email;
    private String password;
    private String name;
    private String specialization;
    private String contact;

    public Doctor(String username, String email, String password, String name, String specialization, String contact) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.specialization = specialization;
        this.contact = contact;
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

    public String getSpecialization() {
        return specialization;
    }

    public String getContact() {
        return contact;
    }
}
