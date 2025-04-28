package model;

import java.io.Serializable;

public class Staff implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String email;
    private String password;
    private String name;
    private String role; // Admin, Receptionist, Dispatcher
    private String contact;

    public Staff(String username, String email, String password, String name, String role, String contact) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public String getContact() {
        return contact;
    }
}
