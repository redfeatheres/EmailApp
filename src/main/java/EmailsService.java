import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class EmailsService {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASS_LENGTH = 8;
    private static final String STORAGE_FILE = "emails.mails";
    private static Map<User, Email> emails = new HashMap<>();

    public EmailsService() {
        loadEmailsMap();
    }

    public void saveEmail() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(STORAGE_FILE))) {
            oos.writeObject(emails);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadEmailsMap() {
        File file = new File(STORAGE_FILE);
        if (!file.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(STORAGE_FILE))) {
            Map<User, Email> loaded = (Map<User, Email>) ois.readObject();
            emails.clear();
            emails.putAll(loaded);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<User, Email> getAllEmails() {
        return emails;
    }

    public Email getEmail(User user) {
        return emails.get(user);
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

    public void listOfEmails() {
        if (!emails.isEmpty()) {
            emails.values().forEach(System.out::println);
        }
    }

    public User readUserFromConsole(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter lastname: ");
        String lastName = scanner.nextLine();
        System.out.println("Select department:");
        Departments[] deps = Departments.values();
        for (int i = 0; i < deps.length; i++) {
            System.out.println((i + 1) + ". " + deps[i]);
        }
        int depIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        String department = deps[depIndex].name();
        return new User(name, lastName, department);
    }
}
