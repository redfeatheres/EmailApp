import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASS_LENGTH = 8;

    public static Map<User, Email> emails = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name:");
        String name = scanner.nextLine();
        System.out.println("Enter your lastname:");
        String lastName = scanner.nextLine();
        System.out.println("Select your department:");
        int i = 0;
        for (Departments dep : Departments.values()) {
            System.out.println(++i + ". " + dep);
        }
        int selectDep = scanner.nextInt();
        String department = String.valueOf(Departments.values()[selectDep - 1]);

        boolean isSuccess = createNewEmail(new User(name, lastName, department));

        listOfEmails();
    }

    public static boolean createNewEmail(User user) {
        return emails.put(user, new Email(passwordGenerator(PASS_LENGTH), emailGenerator(user))) != null;
    }

    public static String emailGenerator(User user) {
        return String.format(
                "%s.%s@%s.company.com",
                user.getName(),
                user.getLastName(),
                user.getDepartment()).toLowerCase();
    }

    public static String passwordGenerator(int length) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            stringBuilder.append(CHARACTERS.charAt(index));
        }
        return stringBuilder.toString();
    }

    public static void listOfEmails () {
        emails.values().forEach(System.out::println);
    }
}
