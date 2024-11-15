package simcard;

import contact.Contact;

public class SimCard {

    private final String name;

    private final Contact[] contacts;

    public SimCard(String name, int slots) {
        this.name = name;
        this.contacts = new Contact[slots];
    }

    public String getName(){
        return name;
    }

    public Contact[] getContacts() {
        return contacts;
    }
}
