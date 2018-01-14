import EDA1.Queue.ArrayQueue;
import EDA1.Queue.OverflowException;

import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {
        Scanner intScan = new Scanner(System.in);
        Scanner stringScan = new Scanner(System.in);

        String menuStr = "" +
                "(1) - Adicionar Contacto\n" +
                "(2) - Listar Contactos\n" +
                "(3) - Editar Contacto\n" +
                "(4) - Procurar Telefone\n" +
                "(5) - Exportar para Queue\n" +
                "(6) - Remover Contacto\n" +
                "(0) - Sair\n" +
                "-> ";
        String nomeContacto = "Nome do Contacto: ";
        String numeroContacto = "Numero do Contacto: ";
        String nomeOuNumero = "\t(1) Editar Nome\n\t(2) Editar Numero\n\t-> ";
        String alterarNumero = "Numero a alterar: ";

        Phonebook test1 = new Phonebook();

        int option;

        do {
            System.out.print(menuStr);
            option = intScan.nextInt();
            if (option == 1) {
                System.out.print(nomeContacto);
                String nome = stringScan.nextLine();
                if(!test1.contains(nome)) {
                    System.out.print(numeroContacto);
                    String numero = stringScan.nextLine();
                    test1.insert(new Contact(nome, numero));
                } else {
                    System.out.println("Contact already exist");
                }
            } else if (option == 2) {
                test1.order();
            } else if (option == 3) {
                test1.order();
                System.out.print(nomeContacto);
                String nome = stringScan.nextLine();
                if (test1.contains(nome)) {
                    System.out.print(nomeOuNumero);
                    option = intScan.nextInt();
                    if (option == 1) {
                        System.out.print("Novo " + nomeContacto);
                        String novoNome = stringScan.nextLine();
                        Contact c = test1.getContact(nome);
                        c.editName(novoNome);
                    } else if (option == 2) {
                        Contact c = test1.getContact(nome);
                        c.getNumbers();
                        System.out.print("\n" + alterarNumero);
                        String numeroVelho = stringScan.nextLine();
                        if (c.contains(numeroVelho)) {
                            System.out.print("Novo " + numeroContacto);
                            String novoNumero = stringScan.nextLine();
                            c.editNumber(numeroVelho, novoNumero);
                        } else
                            System.out.printf("Numero %s não existe no contacto %s\n", numeroVelho, nome);
                    }
                } else {
                    System.out.println("Contact doesn't exist");
                }
            } else if(option == 4) {
                System.out.print(numeroContacto);
                String numero = stringScan.nextLine();
                System.out.printf("Contacto associado a %s:\n%s\n", numero, test1.getName(numero));
            } else if(option == 5) {
                ArrayQueue<Contact> queue;
                boolean exported = true;
                try {
                    queue = test1.phonebookToQueue();
                } catch (OverflowException o) {
                    o.printStackTrace();
                    exported = false;
                }
                System.out.printf("Exportação %s sucedida (%d Contactos Exportados)\n", exported ? "bem" : "mal", test1.getSize());
            } else if(option == 6) {
                test1.order();
                System.out.print(nomeContacto);
                String nome = stringScan.nextLine();
                test1.remove(nome);
                if(!test1.contains(nome))
                    System.out.println("Contacto removido com sucesso");
            }
        } while (option != 0);
    }


}
