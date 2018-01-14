import EDA1.AVL.ArvVL;
import EDA1.Queue.ArrayQueue;
import EDA1.Queue.OverflowException;
import java.util.Scanner;


public class Phonebook {
    ArvVL<Contact> phonebook;
    int size;
    Scanner scanner;

    Phonebook(Contact user) {
        phonebook = new ArvVL<>(user);
        size = 1;
    }

    Phonebook() {
        phonebook = new ArvVL<>();
        size = 0;
    }

    public void insert(Contact contact) {
        phonebook.insert(contact);
        size++;
    }

    public Contact getContact(String name) {
        for (Contact c : phonebook) {
            if (c.name.equals(name)) {
                return c;
            }
        }
        return null;
    }

    public String getName(String number) {
        String name_s = " - ";
        for (Contact c : phonebook) {
            if (c.contains(number))
                name_s += c.name + "\n - ";
        }
        return (name_s.length() > 3) ? name_s.substring(0, name_s.length() - 4) : "Nenhum Contacto Encontrado";
    }

    public void remove(String name) {
        for (Contact c : phonebook) {
            if (c.name.equals(name)) {
                phonebook.remove(new Contact(name));
                size--;
                System.out.printf("Contact %s successfully removed from contacts\n", name);
                return;
            }
        }
        System.out.printf("Contact %s doesn't exists!\n", name);
    }

    public void getNumbers(String name) {
        for (Contact c : phonebook) {
            if (c.name.equals(name)) {
                c.getNumbers();
                return;
            }
        }
        System.out.printf("Contact %s doesn't exists!\n", name);
    }

    public void order() {
        for (Contact c : phonebook) {
            System.out.print(c.name + ": ");
            c.getNumbers();
            System.out.println();
        }
    }

    public int getSize() {
        return size;
    }

    public ArrayQueue<Contact> phonebookToQueue() throws OverflowException {
        ArrayQueue<Contact> p = new ArrayQueue<>(size);
        for (Contact c : phonebook)
            p.enqueue(c);
        return p;
    }

    public boolean contains(String nome) {
        for (Contact c : phonebook) {
            if (c.name.equals(nome))
                return true;
        }
        return false;
    }
}