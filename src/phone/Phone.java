package phone;

import contact.Contact;
import simcard.SimCard;

import java.util.Scanner;

public class Phone {

    private Scanner scanner = new Scanner(System.in);

    private final String name;

    private final Contact[] contacts;

    private final SimCard[] simCards;

    public Phone(String name, int contactCapacity, int simSlot) {
        if (!(simSlot == 1 || simSlot == 2))
            throw new RuntimeException("Sim card count should be 1 or 2");
        this.name = name;
        this.contacts = new Contact[contactCapacity];
        this.simCards = new SimCard[simSlot];
    }

    public SimCard[] getSimCards() {
        return simCards;
    }

    public Contact[] getContacts() {
        return contacts;
    }

    public void insertSim() {
        System.out.println("How many sim cards do you want to insert?");
        int insertingCnt = scanner.nextInt();
        if (insertingCnt > simCards.length || insertingCnt < 1) {
            System.out.println("You can't insert: " + insertingCnt);
            return;
        }

        simCards[0] = createSim();

        if (insertingCnt == 2)
            simCards[1] = createSim();
    }

    private SimCard createSim() {
        System.out.println("How many contact can store?");
        int contactSlot = scanner.nextInt();
        scanner = new Scanner(System.in);

        System.out.println("Enter name of sim card");
        String simName = scanner.nextLine();
        return new SimCard(simName, contactSlot);
    }
}