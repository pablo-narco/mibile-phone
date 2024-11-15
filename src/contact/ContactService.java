package contact;

import phone.Phone;
import simcard.SimCard;

import java.util.Scanner;

public class ContactService {

    private static final Scanner scanner = new Scanner(System.in);

    private final Phone phone;

    public ContactService(Phone phone) {
        this.phone = phone;
    }

    public void add() {
        System.out.println("Choose saving place: ");
        System.out.println("1=> Phone");

        SimCard[] simCards = phone.getSimCards();
        System.out.println("2=> " + simCards[0].getName());

        boolean secondHas = simCards[1] != null;
        if (secondHas)
            System.out.println("3=> " + simCards[1].getName());

        int com = scanner.nextInt();
        if (com < 1 || !secondHas && com > 2 || com > 3) {
            System.err.println("Choose correct place");
            add();
            return;
        }

        if (com == 1)
            insertContact(phone.getContacts());
        else if (com == 2)
            insertContact(simCards[0].getContacts());
        else
            insertContact(simCards[1].getContacts());
    }

    public void edit() {
        System.out.println("Enter the contact name to edit: ");
        String name = scanner.next();

        Contact contact = search(name);
        if(contact != null){
            System.out.println("Editing contact: " + contact);

            Contact updatedContact = getContactInfoFromConsole();
            contact.setName(updatedContact.getName());
            contact.setName(updatedContact.getNumber());
            System.out.println("Contact updated: " + contact);

        }else{
            System.out.println("Contact not found");
        }

    }

    public void list() {
        System.out.println("Listing all contact:");

        listContacts(phone.getContacts());

    for (SimCard simCard : phone.getSimCards()) {
        if(simCard != null){
            listContacts(simCard.getContacts());
        }
    }
}

    public void search() {
        System.out.println("Enter the contact name to search: ");
        String name = scanner.next();

        Contact contact = search(name);
        if(contact != null){
            System.out.println("Contact found: " + contact);
        }else{
            System.out.println("Contact not found");                                                                     
        }
    }

    public void delete() {
        System.out.println("Enter the contact name to delete: ");
        String name = scanner.next();

        Contact contact = search(name);
        if(contact != null){
            removeContact(contact);
            System.out.println("Contact deleted: " + contact);
        }else{
            System.out.println("Contact not found");
        }
    }

    private void insertContact(Contact[] contacts, Contact contact) {
        for (int i = 0; i < contacts.length; i++) {
            if (contacts[i] == null) {
                contacts[i] = contact;
                System.out.println("Contact added: " + contact);
                return;
                
            }
        }
    }

    private boolean checkName(Contact[] contacts, Contact contact) {
        //todo
        for(Contact c : contacts){
            if(c != null && c.getName().equalsIgnoreCase(contact.getName())){
                return false;
            }
        }
        return true;
    }

    private Contact getContactInfoFromConsole() {
        //todo
        System.out.println("Enter the contact name: ");
        String name = scanner.next();
        System.out.println("Enter the phone number: ");
        String phoneNumber = scanner.next();
        return new Contact(name, phoneNumber);
    }

    private boolean checkSpace(Contact[] contacts) {
        for (Contact contact : contacts)
            if (contact == null)
                return true;

        return false;
    }

    private void insertContact(Contact[] contacts) {

        boolean hasSpace = checkSpace(contacts);
        if (!hasSpace) {
            System.out.println("No space on the phone");
            add();
            return;
        }

        Contact contact = getContactInfoFromConsole();

        boolean unique = checkName(contacts, contact);
        if (!unique) {
            System.out.println("Name of contact already exists");
            add();
            return;
        }

        insertContact(contacts, contact);
    }
    private Contact search(String name)  {
        for(Contact contact : phone.getContacts()){
            if(contact != null && contact.getName().equalsIgnoreCase(name)){
                return contact;
            }
        }
        for (SimCard simCard : phone.getSimCards()) {
            if(simCard != null ){
                for(Contact contact : simCard.getContacts()){
                    if(contact != null && contact.getName().equalsIgnoreCase(name)){
                        return contact;
                    }
                }
            }
        }
        return null;
    }
    private void listContacts(Contact[] contacts) {
        for(Contact contact : contacts){
            if(contact != null){
                System.out.println(contact);
            }
        }
    }
    private void removeContact(Contact contact) {
        for(int i = 0; i < phone.getContacts().length; i++){
            if(phone.getContacts()[i] != null && phone.getContacts()[i].equals(contact)){
                phone.getContacts()[i] = null;
                return;
            }
        }
        for(SimCard simCard : phone.getSimCards()){
            if(simCard != null){
                for(int i = 0; i < simCard.getContacts().length; i++){
                    if(simCard.getContacts()[i] != null && simCard.getContacts()[i].equals(contact)){
                        simCard.getContacts()[i] = null;
                        return;
                    }
                }
            }
        }
        
    }
}
