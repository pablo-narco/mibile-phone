package contact;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {

    private String name;

    private String number;

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;

    }

    public String getNumber() {
        return this.number;
    }

    public void setName(String name) {
        if (name == null){
            System.out.println("Name should not be null");
            return;
        }
        String regex = "^[A-Z][a-z'\\d]{1,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);

        if(!matcher.find()){
            System.out.println("Name length should not be less than 2 or start with upper");
            return;
        }

        this.name = name;
    }

    @Override
    public String toString() {
        return "Contact information: \"" + name + "\" : \"" + number + "\"";
    }



}
