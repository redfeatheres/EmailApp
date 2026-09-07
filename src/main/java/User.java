import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private String name;
    private String lastName;
    private String department;

    public User(String name, String lastName, String department) {
        this.name = name;
        this.lastName = lastName;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(lastName, user.lastName) && Objects.equals(department, user.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, department);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
