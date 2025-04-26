package model;

import java.io.Serializable;

public class Appointment implements Serializable {
    private static final long serialVersionUID = 1L;

    private String patientUsername;
    private String doctorUsername;
    private String date;
    private String time;
    private String notes;
    private String resourceName; // Room name if any

    public Appointment(String patientUsername, String doctorUsername, String date, String time, String notes, String resourceName) {
        this.patientUsername = patientUsername;
        this.doctorUsername = doctorUsername;
        this.date = date;
        this.time = time;
        this.notes = notes;
        this.resourceName = resourceName;
    }

    public String getPatientUsername() {
        return patientUsername;
    }

    public String getDoctorUsername() {
        return doctorUsername;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getNotes() {
        return notes;
    }

    public String getResourceName() {
        return resourceName;
    }
}
