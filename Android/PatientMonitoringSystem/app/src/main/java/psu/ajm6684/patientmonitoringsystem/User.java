package psu.ajm6684.patientmonitoringsystem;

public class User {


    private String department;
    private String email;
    private String fullName;
    private String hospital;
    private String position;

    public User() {
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public User(String department, String email, String fullName, String hospital, String position) {
        this.department = department;
        this.email = email;
        this.fullName = fullName;
        this.hospital = hospital;
        this.position = position;
    }
}
