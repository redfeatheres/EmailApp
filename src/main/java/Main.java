import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmailsService emailsService = new EmailsService();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        // MENU
        while (running) {
            System.out.println("""
                    ______________
                    Choice action:
                    ______________
                    1. Add a new user
                    2. View list of emails
                    3. Exit
                    """);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
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

                    boolean isSuccess = EmailsService.createNewEmail(new User(name, lastName, department));
                }
                case 2 -> {
                    emailsService.listOfEmails();
                }
                case 3 -> {
                    running = false;
                }
                default -> System.out.println("Wrong choice");
            }

        }
        emailsService.saveEmail();
        scanner.close();
    }
}
