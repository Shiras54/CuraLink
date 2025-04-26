package controller;

import model.*;
import java.io.*;
import java.util.ArrayList;

public class DataManager {

    public static <T> void saveData(ArrayList<T> list, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> loadData(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (ArrayList<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    // Specialized short methods
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
}
