package psu.ajm6684.patientmonitoringsystem;

public class Note {
    private String patientName;
    private String description;

    public Note(){}

    public Note(String patientName, String description) {
        this.patientName = patientName;
        this.description = description;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
