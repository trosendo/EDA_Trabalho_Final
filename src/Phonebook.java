import EDA1.ABP.ArvBP;

import java.util.Scanner;

public class Phonebook extends ArvBP {
    ArvBP<Contact> phonebook;
    int size;

    Phonebook(Contact user){
        phonebook = new ArvBP<>(user);
        size = 1;
    }
    Phonebook(){
        phonebook = new ArvBP<>();
        size = 0;
    }

    public void insertIntoExisting(String name, String number){
        boolean inserted = false;
        boolean existsButNotAdded = false;
        for(Contact c : phonebook) {
            if (c.name.equals(name)) {
                if(c.contains(number)) {
                    System.out.printf("Contact %s already has %s in its numbers\n", name, number);
                    existsButNotAdded = true;
                    break;
                }
                c.addNumber(number);
                inserted = true;
                break;
            }
        }
        if(inserted) {
            System.out.printf("Phone number %s successfully added to contact %s \n", number, name);
        }
        else if(!existsButNotAdded)
            System.out.printf("Contact %s doesn't exists!\n", name);

        /*
        Contact temp = phonebook.getElement(new Contact(name));
        if(temp != null){
            temp.addNumber(number);
            System.out.printf("Phone number %s successfully added to contact %s \n", number, name);
        }else{
            System.out.printf("Contact %s doesn't exists!\n", name);
        }*/
    }
    public void insertIntoExisting(String name, ArvBP<String> numbers){ //unnecessary
        while(!numbers.isEmpty()){
            String number = numbers.findMin();
            insertIntoExisting(name, number);
            numbers.remove(number);
        }
    }

    public void insert(Contact contact){
        if(!phonebook.contains(contact)) {
            phonebook.insert(contact);
            size++;
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.printf("\nContact %s already exists!\n" +
                            "Do you wish to add the following number(s) into the existing contact?\n",
                    contact.name);
            contact.phoneNumbers.printInOrder();
            System.out.print("(Yes or No): ");
            String input = scanner.next();
            if(input.equalsIgnoreCase("yes"))
                insertIntoExisting(contact.name, contact.phoneNumbers);

        }
    }

    public void remove(String name){
        boolean removed = false;
        for(Contact c : phonebook){
            if(c.name.equals(name)){
                phonebook.remove(new Contact(name));
                removed = true;
                size--;
                break;
            }
        }
        if(removed) {
            System.out.printf("Contact %s successfully removed from contacts\n", name);
        }
        else {
            System.out.printf("Contact %s doesn't exists!\n", name);
        }
        //phonebook.remove(phonebook.getElement(new Contact(name)));
    }

    public void getNumbers(String name){
        for(Contact c : phonebook){
            if(c.name.equals(name)){
                c.getNumbers();
                break;
            }
        }
        //phonebook.getElement(new Contact(name)).getNumbers();
    }

    public String getCallerID(String callerNumber){
        for(Contact c : phonebook){
            if(c.phoneNumbers.contains(callerNumber))
                return c.name;
        }
        return "UNKNOWN (" + callerNumber + ")";
    }

    public int getSize(){
        return size;
    }
}
