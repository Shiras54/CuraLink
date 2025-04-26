package model;

import java.io.Serializable;

public class Resource implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String type; // Room, Equipment, Ambulance
    private String status; // Available, In Use, Maintenance, etc.
    private String assignedPatientUsername; // Optional, for Room assignments
    private String assignedDoctorUsername; // Optional, if needed

    public Resource(String name, String type, String status) {
        this.name = name;
        this.type = type;
        this.status = status;
        this.assignedPatientUsername = "";
        this.assignedDoctorUsername = "";
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public String getAssignedPatientUsername() {
        return assignedPatientUsername;
    }

    public String getAssignedDoctorUsername() {
        return assignedDoctorUsername;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void assignPatient(String patientUsername) {
        this.assignedPatientUsername = patientUsername;
    }

    public void assignDoctor(String doctorUsername) {
        this.assignedDoctorUsername = doctorUsername;
    }
}
