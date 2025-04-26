package util;

import model.*;

public class SessionManager {
    private static Patient loggedPatient = null;
    private static Doctor loggedDoctor = null;
    private static Staff loggedStaff = null;

    public static void setLoggedPatient(Patient patient) {
        loggedPatient = patient;
    }

    public static void setLoggedDoctor(Doctor doctor) {
        loggedDoctor = doctor;
    }

    public static void setLoggedStaff(Staff staff) {
        loggedStaff = staff;
    }

    public static Patient getLoggedPatient() {
        return loggedPatient;
    }

    public static Doctor getLoggedDoctor() {
        return loggedDoctor;
    }

    public static Staff getLoggedStaff() {
        return loggedStaff;
    }

    public static void logout() {
        loggedPatient = null;
        loggedDoctor = null;
        loggedStaff = null;
    }
}
