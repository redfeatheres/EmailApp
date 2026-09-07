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
                    CHOICE ACTION:
                    ______________
                    1. ADD a NEW USER
                    2. VIEW LIST of emails / LIST KYES
                    3. CHANGE mailbox CAPACITY (default=100)
                    4. SET ALT EMAIL
                    5. EXIT
                    """);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    scanner.nextLine();
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
                    scanner.nextLine();
                    String department = String.valueOf(Departments.values()[selectDep - 1]);

                    boolean isSuccess = EmailsService.createNewEmail(new User(name, lastName, department));
                }
                case 2 -> {
                    emailsService.listOfEmails();
                    for(User u : emailsService.getAllEmails().keySet()){
                        System.out.println(u);
                    }
                }
                case 3 -> {
                    scanner.nextLine();
                    User user = emailsService.readUserFromConsole(scanner);

                    System.out.println("Enter capacity:");
                    int inputCapasity = scanner.nextInt();

                    Email email = emailsService.getEmail(user);
                    if (email != null) {
                        email.setBoxCapacity(inputCapasity);
                        System.out.println("Capacity updated.");
                    } else {
                        System.out.println("User not found");
                    }
                }
                case 4 -> {
                    scanner.nextLine();
                    User user = emailsService.readUserFromConsole(scanner);
                    System.out.println("Enter alternate email address:");
                    String alternateEmail = scanner.nextLine();
                    if (emailsService.getEmail(user) != null) {
                        emailsService.getEmail(user).setAltEmail(alternateEmail);
                        System.out.println("Successful set alternative email");
                    } else {
                        System.out.println("User not found");
                    }
                }
                case 5 -> {
                    running = false;
                }
                default -> System.out.println("Wrong choice");
            }
        }
        emailsService.saveEmail();
        scanner.close();
    }
}
