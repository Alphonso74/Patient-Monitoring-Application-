package psu.ajm6684.patientmonitoringsystem;

public class Note {
    private String patientName;
    private String description;
    private String height;
    private Integer weight;
    private Integer rHeartRate;
    private String triageTag;
    private String activeNurse;
    private Integer BodyTempature;
    private String medications;
    private String surgicaHistory;
    private String standingOrder;

    public Note(String patientName, String description, String height, Integer weight, Integer rHeartRate, String triageTag, String activeNurse, Integer bodyTempature, String medications, String surgicaHistory, String standingOrder) {
        this.patientName = patientName;
        this.description = description;
        this.height = height;
        this.weight = weight;
        this.rHeartRate = rHeartRate;
        this.triageTag = triageTag;
        this.activeNurse = activeNurse;
        BodyTempature = bodyTempature;
        this.medications = medications;
        this.surgicaHistory = surgicaHistory;
        this.standingOrder = standingOrder;
    }

    public Integer getBodyTempature() {
        return BodyTempature;
    }

    public void setBodyTempature(Integer bodyTempature) {
        BodyTempature = bodyTempature;
    }

    public String getMedications() {
        return medications;
    }

    public void setMedications(String medications) {
        this.medications = medications;
    }

    public String getSurgicaHistory() {
        return surgicaHistory;
    }

    public void setSurgicaHistory(String surgicaHistory) {
        this.surgicaHistory = surgicaHistory;
    }

    public String getStandingOrder() {
        return standingOrder;
    }

    public void setStandingOrder(String standingOrder) {
        this.standingOrder = standingOrder;
    }

    public String getActiveNurse() {
        return activeNurse;
    }

    public void setActiveNurse(String activeNurse) {
        this.activeNurse = activeNurse;
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
