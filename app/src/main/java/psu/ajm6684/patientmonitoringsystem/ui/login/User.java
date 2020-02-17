package psu.ajm6684.patientmonitoringsystem.ui.login;

public class User {



    public String hospital;
    public String firstName;
    public String lastName;
    public String position;
    public String userName;
    public String department;
    public String employeeID;

    //required default constructor
    public User() {
    }

    public User(String hospital, String firstName, String lastName, String position, String userName, String department, String employeeID) {
        this.hospital = hospital;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.userName = userName;
        this.department = department;
        this.employeeID = employeeID;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
}