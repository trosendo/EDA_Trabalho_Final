package EDA1.ABP;

public class Contact implements Comparable<Contact> {
    String name;
    ArvBP<String> phoneNumbers;

    Contact(String name, String phoneNumber){
        this.name = name;
        phoneNumbers = new ArvBP<>(phoneNumber);
    }
    Contact(String name){
        this.name = name;
        phoneNumbers = new ArvBP<>();
    }

    public void addNumber(String number){
        phoneNumbers.insert(number);
    }

    public void getNumbers(){
        phoneNumbers.printInOrder();
    }

    @Override
    public int compareTo(Contact o) {
        return name.compareTo(o.name);
    }

    public String toString(){
        return name;
    }

    public boolean contains(String number){
        return phoneNumbers.contains(number);
    }

}
