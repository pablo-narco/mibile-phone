import contact.ContactService;
import phone.Phone;

import java.util.Scanner;

public class Demo {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Phone phone = new Phone("A55", 10, 2);
        phone.insertSim();

        ContactService contactService = new ContactService(phone);
        while (true) {
            int com;
            System.out.println("""
                    Choose commands:
                    0=> Terminate,
                    1 => Add, 2 => Edit,
                    3=> List, 4 => Search,
                    5 => Delete
                    """);
            com = scanner.nextInt();
            switch (com) {
                case 0:
                    return;
                case 1:
                    contactService.add();
                    break;
                case 2:
                    contactService.edit();
                    break;
                case 3:
                    contactService.list();
                    break;
                case 4:
                    contactService.search();
                    break;
                case 5:
                    contactService.delete();
                    break;
                default:
                    System.out.println("Choose correct command");
            }
        }
    }
}
