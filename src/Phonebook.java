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
        for(Contact c : phonebook) {
            if(c.contains(number))
                name_s += c.name + "\n - ";
        }
        return (name_s.length() > 3) ? name_s.substring(0, name_s.length() - 4) : "Nenhum Contacto Encontrado";
    }
/*
    public void insertIntoExisting(String name, String number) {
        for (Contact c : phonebook) {
            if (c.name.equals(name)) {
                c.addNumber(number);
                return;
            }
        }
        System.out.println("Contact doesn't exist");
    }*/

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

    public boolean contains(String nome){
        for(Contact c : phonebook){
            if(c.name.equals(nome))
                return true;
        }
        return false;
    }

/*
    public void adicionar() {
        scanner = new Scanner(System.in);
        System.out.println("Diga o Nome do contacto");
        String name = scanner.nextLine();
        System.out.println("Diga o numero");
        String number = scanner.nextLine();
        Contact c = new Contact(name, number);
        insert(c);
        menu();
    }

    public void editar() {
        scanner = new Scanner(System.in);
        System.out.println("Selecione o Contacto");
        order();
        String name = scanner.nextLine();
        System.out.println("Quer modificar: \n (1) - Nome \n (2) - Numbero?");
        int option = scanner.nextInt();
        if (option == 1) {
            scanner = new Scanner(System.in);
            System.out.println("Indique o Nome");
            String newName = scanner.nextLine();
            getContact(name).editContact(newName);
            order();
            menu();

        }
        if (option == 2) {
            scanner = new Scanner(System.in);
            System.out.println("Quer: \n (1) - Remover \n (2) - adicionar \n (3)  - Editar");
            int option2 = scanner.nextInt();
            if (option2 == 1) {
                scanner = new Scanner(System.in);
                System.out.println("Qual numero?");
                getContact(name).getNumbers();
                String remNumber = scanner.nextLine();
                getContact(name).removeNumber(remNumber);
                order();

            }
        }
    }

    public void menu() {
        System.out.println("Selecione: \n (1) - adicionar Contacto \n (2) - Listar Contactos \n (3) - Editar Contactos");
        scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        if (option == 1)
            adicionar();
        if (option == 2)
            order();
            menu();
        if (option == 3)
            editar();
    }


    public static void main(String[] args) {
        Phonebook p = new Phonebook();
        p.menu();
    }*/
}