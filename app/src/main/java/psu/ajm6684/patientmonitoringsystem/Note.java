package psu.ajm6684.patientmonitoringsystem;

public class Note {
    private String patientName;
    private String description;
    private String height;
    private Integer weight;
    private Integer rHeartRate;
    private String triageTag;

    public Note(String patientName, String description, String height, Integer weight, Integer rHeartRate, String triageTag) {
        this.patientName = patientName;
        this.description = description;
        this.height = height;
        this.weight = weight;
        this.rHeartRate = rHeartRate;
        this.triageTag = triageTag;
    }

    public String getTriageTag() {
        return triageTag;
    }

    public void setTriageTag(String triageTag) {
        this.triageTag = triageTag;
    }

    public Note(){}

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getrHeartRate() {
        return rHeartRate;
    }

    public void setrHeartRate(Integer rHeartRate) {
        this.rHeartRate = rHeartRate;
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
