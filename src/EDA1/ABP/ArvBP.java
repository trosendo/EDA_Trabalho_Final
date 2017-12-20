package EDA1.ABP;

import java.util.Iterator;

public class ArvBP<T extends Comparable<? super T>> implements Iterable<T>, ABP<T> {

    private ABPNode<T> root;
    private int size;

    public ArvBP(T x) {
        root = new ABPNode<>(x);
        size = 1;
    }

    public ArvBP(ABPNode<T> r) {
        root = r;
        size = 0;
    }

    public ArvBP() {
        root = null;
        size = 0;
    }

    public ArvBP(T r, ABPNode<T> e, ABPNode<T> d) {
        root = new ABPNode<>(r, e, d);
        size = 3;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    private T getElement(T x) {
        return getElement(x, root);
    }

    private T getElement(T x, ABPNode<T> node){
        if(node == null)
            return null;

        if(node.element.compareTo(x) == 0)
            return node.element;

        if(node.element.compareTo(x) > 0)
            return getElement(x, node.left);
        else
            return getElement(x, node.right);

    }

    @Override
    public boolean contains(T x) {
        return contains(x, root);
    }

    private boolean contains(T x, ABPNode<T> n){
        if (n == null)
            return false;
        if (n.element.compareTo(x) == 0)
            return true;

        if (x.compareTo(n.element) < 0)    // if(x < root.element)
            return contains(x, n.left);
        else                               // if(x > root.element)
            return contains(x, n.right);

    }

    @Override
    public T findMin() {
        return findMin(root).element;
    }

    private ABPNode<T> findMin(ABPNode<T> n) {
        ABPNode<T> current = n;
        while(current.left != null){
            current = current.left;
        }
        return current;
    }

    @Override
    public T findMax() {
        return findMax(root).element;
    }

    private ABPNode<T> findMax(ABPNode<T> n) {
        ABPNode<T> current = n;
        while(current.right != null){
            current = current.right;
        }
        return current;
    }

    @Override
    public void insert(T x) {
        size++;
        root = insert(x, root);
    }

    private ABPNode<T> insert(T x, ABPNode<T> n) {
        if (n == null)
            return new ABPNode<>(x);

        if (n.element.compareTo(x) == 0)
            return n;

        if (x.compareTo(n.element) < 0){     // if(x < root.element)
            n.left = insert(x, n.left);
            return n;
        } else {                                // if(x > root.element)
            n.right = insert(x, n.right);
            return n;
        }
    }

    @Override
    public void remove(T x) {
        size--;
        root = remove(x, root);
    }

    private ABPNode<T> remove(T x, ABPNode<T> n){
        if(n == null)
            return null;

        if(x.compareTo(n.element) == 0){     // if(x == n.element)
            // n is the node to be removed
            if(n.left == null && n.right == null){
               return null;
            }

            if(n.left == null){
               return n.right;
            }

            if(n.right == null){
               return n.left;
            }

            /*
            if the code gets here then the node n has 2 children.
            find the smallest node by reading the most left
            child of the right subtree.
            */

            ABPNode<T> smallestNode = findMin(n.right);

            n.element = smallestNode.element;
            n.right = smallestNode.right;

            n.right = remove(smallestNode.element, n.right);

            return n;
        } else if(x.compareTo(n.element) < 0){ // if(x < n.element)
            n.left = remove(x, n.left);
            return n;
        } else {
            n.right = remove(x, n.left);
            return n;
        }
    }

    @Override
    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(ABPNode<T> node){ //RETURN STRING WITH PRINTED ELEMENTS!!
        if(node == null)
            return;

        printInOrder(node.left);
        System.out.println("-> " + node.element);
        printInOrder(node.right);
    }

    @Override
    public void printPostOrder() {

    }

    @Override
    public void printPreOrder() {

    }

    @Override
    public Iterator<T> iterator() {
        return new ABPIterator<>(root, size);
    }

    public int getSize(){
        return size;
    }
}
