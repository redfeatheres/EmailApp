import java.io.Serializable;

public class Email implements Serializable {

    private String password;
    private String email;
    private int boxCapacity;
    private String altEmail;

    public Email(String password, String email) {
        altEmail = "";
        boxCapacity = 100;
        this.password = password;
        this.email = email;
    }

    public int getBoxCapacity() {
        return boxCapacity;
    }

    public void setBoxCapacity(int boxCapacity) {
        this.boxCapacity = boxCapacity;
    }

    public String getAltEmail() {
        return altEmail;
    }

    public void setAltEmail(String altEmail) {
        this.altEmail = altEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "password='" + password + '\'' +
                ", email='" + email + '\'';
    }
}
