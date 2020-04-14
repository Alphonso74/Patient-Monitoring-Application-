package psu.ajm6684.patientmonitoringsystem;

public class user {


    private String id;
    private  String username;
    private String imageURL;


    public user (String id, String username, String imageURL) {
        this.id = id;
        this.username = username;
        this.imageURL = imageURL;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public user() {


    }
}

