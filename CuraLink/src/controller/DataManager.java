package controller;

import model.*;

import java.io.*;
import java.util.ArrayList;

public class DataManager {
	public static void ensureDefaultAdmin() {
	    ArrayList<Staff> staffList = loadStaff();

	    boolean hasAdmin = false;
	    for (Staff staff : staffList) {
	        if (staff.getRole().equals("admin")) {
	            hasAdmin = true;
	            break;
	        }
	    }

	    if (!hasAdmin) {
	        Staff defaultAdmin = new Staff("Default Admin", "admin@curalink.com", "admin123", "admin");
	        staffList.add(defaultAdmin);
	        saveData(staffList, "staff.ser");
	        System.out.println("✅ Default admin created: admin@curalink.com / admin123");
	    }
	}


    // Generic Save All
    public static <T> void saveData(ArrayList<T> list, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Generic Load
    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> loadData(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (ArrayList<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    // Load Methods
    public static ArrayList<Patient> loadPatients() {
        return loadData("patients.ser");
    }

    public static ArrayList<Doctor> loadDoctors() {
        return loadData("doctors.ser");
    }

    public static ArrayList<Staff> loadStaff() {
        return loadData("staff.ser");
    }

    public static ArrayList<Appointment> loadAppointments() {
        return loadData("appointments.ser");
    }

    public static ArrayList<Billing> loadBillings() {
        return loadData("billings.ser");
    }

    public static ArrayList<EmergencyRequest> loadEmergencyRequests() {
        return loadData("emergencies.ser");
    }

    public static ArrayList<Resource> loadResources() {
        return loadData("resources.ser");
    }

    public static ArrayList<DoctorAvailability> loadDoctorAvailabilities() {
        return loadData("availabilities.ser");
    }

    // 🔥 Save Single User Methods
    public static void savePatient(Patient patient) {
        ArrayList<Patient> patients = loadPatients();
        patients.add(patient);
        saveData(patients, "patients.ser");
    }

    public static void saveDoctor(Doctor doctor) {
        ArrayList<Doctor> doctors = loadDoctors();
        doctors.add(doctor);
        saveData(doctors, "doctors.ser");
    }

    public static void saveStaff(Staff staff) {
        ArrayList<Staff> staffList = loadStaff();
        staffList.add(staff);
        saveData(staffList, "staff.ser");
    }
}
