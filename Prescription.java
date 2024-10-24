import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Prescription {
    private int prescID;
    private String firstName;
    private String lastName;
    private String address;
    private float sphere;
    private float axis;
    private float cylinder;
    private Date examinationDate;
    private String optometrist;
    private String[] remarkTypes = {"Client", "Optometrist"};
    private ArrayList<String> postRemarks = new ArrayList<>();

    public Prescription(int prescID, String firstName, String lastName, String address, float sphere, float axis, float cylinder, Date examinationDate, String optometrist) {
        this.prescID = prescID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.sphere = sphere;
        this.axis = axis;
        this.cylinder = cylinder;
        this.examinationDate = examinationDate;
        this.optometrist = optometrist;
    }

    // Method to add Prescription to a file
    public boolean addPrescription() {
        if (!isValidPrescription()) {
            return false;
        }

        try (FileWriter writer = new FileWriter("presc.txt", true)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
            writer.write("Prescription ID: " + prescID + "\n");
            writer.write("First Name: " + firstName + "\n");
            writer.write("Last Name: " + lastName + "\n");
            writer.write("Address: " + address + "\n");
            writer.write("Sphere: " + sphere + "\n");
            writer.write("Cylinder: " + cylinder + "\n");
            writer.write("Axis: " + axis + "\n");
            writer.write("Examination Date: " + dateFormat.format(examinationDate) + "\n");
            writer.write("Optometrist: " + optometrist + "\n");
            writer.write("-------------------------------\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to validate the Prescription information
    private boolean isValidPrescription() {
        if (firstName.length() < 4 || firstName.length() > 15 || lastName.length() < 4 || lastName.length() > 15) {
            return false;
        }
        if (address.length() < 20) {
            return false;
        }
        if (sphere < -20.00 || sphere > 20.00) {
            return false;
        }
        if (cylinder < -4.00 || cylinder > 4.00) {
            return false;
        }
        if (axis < 0 || axis > 180) {
            return false;
        }
        if (optometrist.length() < 8 || optometrist.length() > 25) {
            return false;
        }
        return true;
    }

    // Method to add Remark to a file
    public boolean addRemark(String remarkText, String remarkType) {
        if (!isValidRemark(remarkText, remarkType)) {
            return false;
        }

        try (FileWriter writer = new FileWriter("remark.txt", true)) {
            writer.write("Remark: " + remarkText + "\n");
            writer.write("Remark Type: " + remarkType + "\n");
            writer.write("-------------------------------\n");
            postRemarks.add(remarkText);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to validate the Remark information
    private boolean isValidRemark(String remarkText, String remarkType) {
        if (postRemarks.size() >= 2) {
            return false;
        }
        String[] words = remarkText.split(" ");
        if (words.length < 6 || words.length > 20 || !Character.isUpperCase(remarkText.charAt(0))) {
            return false;
        }
        if (!remarkType.equalsIgnoreCase("Client") && !remarkType.equalsIgnoreCase("Optometrist")) {
            return false;
        }
        return true;
    }
}
