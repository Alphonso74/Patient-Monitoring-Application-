package psu.ajm6684.patientmonitoringsystem;

public class chartNote {

    String userText;
    String patientName;
    String date;
    String time;

    public chartNote(String userText, String patientName, String date, String time) {
        this.userText = userText;
        this.patientName = patientName;
        this.date = date;
        this.time = time;
    }

    public String getUserText() {
        return userText;
    }

    public void setUserText(String userText) {
        this.userText = userText;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
