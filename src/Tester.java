import EDA1.ABP.ArvBP;
import EDA1.AVL.ArvVL;

public class Tester {
    public static void main(String[] args){

        ArvVL<Integer> avl = new ArvVL<>();
        avl.insert(30);
        avl.insert(20);
        avl.insert(7);
        avl.insert(15);
        //avl.insert(15);
        avl.insert(12);
        avl.insert(8);
        avl.insert(10);
        avl.insert(25);
        avl.insert(28);
        avl.insert(29);

        avl.remove(30);

        avl.insert(13);

        //System.out.println(avl.isBalanced(avl.getRoot()));
        avl.printPostOrder();


        /*Phonebook phonebook = new Phonebook(new Contact("António", "911111111"));
        Contact temp = new Contact("António", "123456789");
        temp.addNumber("987654321");
        temp.addNumber("123987456");
        temp.addNumber("911111111");
        phonebook.insert(temp);

        phonebook.insert(new Contact("Tomás", "910748063"));
        phonebook.insert(new Contact("João", "910748111"));
        phonebook.insert(new Contact("Gui", "910748222"));
        phonebook.insert(new Contact("Bernardo", "910748333"));

        System.out.println(phonebook.getCallerID("911111111") + " Size: " + phonebook.getSize());
        phonebook.remove("António");
        System.out.println(phonebook.getCallerID("911111111"));


        //System.out.println("\nInorder traversal of binary tree is ");
        //System.out.println("The maximum value of BST is " + tree.findMin());
        //tree.findMin().getNumbers();
*/
    }


}
