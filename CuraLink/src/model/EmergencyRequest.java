package model;

import java.io.Serializable;

public class EmergencyRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String patientUsername;
    private String location;
    private String severity; // Critical, High, Moderate, Low
    private String description;
    private String status; // Pending, Accepted, Rejected
    private String requestTime; // timestamp as string

    public EmergencyRequest(String patientUsername, String location, String severity, String description, String requestTime) {
        this.patientUsername = patientUsername;
        this.location = location;
        this.severity = severity;
        this.description = description;
        this.status = "Pending";
        this.requestTime = requestTime;
    }

    public String getPatientUsername() {
        return patientUsername;
    }

    public String getLocation() {
        return location;
    }

    public String getSeverity() {
        return severity;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
