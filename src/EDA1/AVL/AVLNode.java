package EDA1.AVL;

public class AVLNode<T> {
    T element;
    AVLNode<T> left;
    AVLNode<T> right;
    int height;
    int balance;

    public AVLNode(T e) {
        element = e;
        left = null;
        right = null;
        height = 0;
        balance = 0;
    }

    public AVLNode(T e, AVLNode<T> left, AVLNode<T> right) {
        element = e;
        this.left = left;
        this.right = right;
        height = 0;
        balance = 0;
    }

    public String toString() {
        return element.toString();
    }

    //Getters init
    public T getElement(){
        return element;
    }

    public AVLNode<T> getLeft() {
        return left;
    }

    public AVLNode<T> getRight() {
        return right;
    }

    public int getHeight(){
        return height;
    }

    public int getBalance(){
        return balance;
    }
    //Getters end

    //Setters init
    public void setElement(T element) {
        this.element = element;
    }

    public void setLeft(AVLNode<T> left) {
        this.left = left;
    }

    public void setRight(AVLNode<T> right) {
        this.right = right;
    }

    public void setHeight(int n){
        height= n;
    }

    public void setBalance(int n){
        balance = n;
    }
    //Setters end
}
