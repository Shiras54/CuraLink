package model;

import java.io.Serializable;

public class DoctorAvailability implements Serializable {
    private static final long serialVersionUID = 1L;

    private String doctorUsername;
    private String weekday;
    private String startTime;
    private String endTime;

    public DoctorAvailability(String doctorUsername, String weekday, String startTime, String endTime) {
        this.doctorUsername = doctorUsername;
        this.weekday = weekday;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getDoctorUsername() {
        return doctorUsername;
    }

    public String getWeekday() {
        return weekday;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}
