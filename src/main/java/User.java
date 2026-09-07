public class User {
    private final String name;
    private final String lastName;
    private final String department;
    private final Email email;

    public User(String name, String lastName, String department, Email email) {
        this.name = name;
        this.lastName = lastName;
        this.department = department;
        this.email = email;
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

    public Email getEmail() {
        return email;
    }
}
