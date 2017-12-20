package EDA1.AVL;

import EDA1.ABP.ABP;
import EDA1.ABP.ABPNode;

import java.util.Iterator;

public class ArvVL<T extends Comparable<? super T>> implements Iterable<T>, ABP<T> {

    private ABPNode<T> root;
    private int size;

    public ArvVL(T x) {
        root = new ABPNode<>(x);
        size = 1;
    }

    public ArvVL(ABPNode<T> r) {
        root = r;
        size = 0;
    }

    public ArvVL() {
        root = null;
        size = 0;
    }

    public ArvVL(T r, ABPNode<T> e, ABPNode<T> d) {
        root = new ABPNode<>(r, e, d);
        size = 3;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean contains(T x) {
        return contains(x, root);
    }

    private boolean contains(T x, ABPNode<T> n){
        if (n == null)
            return false;
        if (n.getElement().compareTo(x) == 0)
            return true;

        if (x.compareTo(n.getElement()) < 0)    // if(x < root.element)
            return contains(x, n.getLeft());
        else                               // if(x > root.element)
            return contains(x, n.getRight());

    }

    @Override
    public T findMin() {
        return findMin(root).getElement();
    }

    private ABPNode<T> findMin(ABPNode<T> n) {
        ABPNode<T> current = n;
        while(current.getLeft() != null){
            current = current.getLeft();
        }
        return current;
    }

    @Override
    public T findMax() {
        return findMax(root).getElement();
    }

    private ABPNode<T> findMax(ABPNode<T> n) {
        ABPNode<T> current = n;
        while(current.getRight() != null){
            current = current.getRight();
        }
        return current;
    }
    @Override
    public void insert(T x) {
        size++;
        root = insert(x, root);
        //check if AVL stays balanced!!!!
        try {
            throw new Exception("CHECK IF BALANCED NEEDED!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ABPNode<T> insert(T x, ABPNode<T> n) {
        if (n == null)
            return new ABPNode<>(x);

        if (n.getElement().compareTo(x) == 0)
            return n;

        if (x.compareTo(n.getElement()) < 0){     // if(x < root.element)
            n.setLeft(insert(x, n.getLeft()));
            return n;
        } else {                                // if(x > root.element)
            n.setRight(insert(x, n.getRight()));
            return n;
        }
    }

    @Override
    public void remove(T x) {

    }

    @Override
    public void printInOrder() {

    }

    @Override
    public void printPostOrder() {

    }

    @Override
    public void printPreOrder() {

    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
