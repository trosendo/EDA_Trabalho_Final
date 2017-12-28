package EDA1.AVL;

import EDA1.ABP.ABP;
import EDA1.ABP.ABPIteratorPostOrder;
import EDA1.Stack.ArrayStack;

import java.util.Iterator;

public class ArvVL<T extends Comparable<? super T>> implements Iterable<T>, ABP<T> {

    private AVLNode<T> root;
    private int size;

    public ArvVL(T x) {
        root = new AVLNode<>(x);
        size = 1;
    }

    public ArvVL(AVLNode<T> r) {
        root = r;
        size = 0;
    }

    public ArvVL() {
        root = null;
        size = 0;
    }

    public ArvVL(T r, AVLNode<T> e, AVLNode<T> d) {
        root = new AVLNode<>(r, e, d);
        size = 3;
    }

    public AVLNode<T> getRoot() {
        return root;
    }



    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean contains(T x) {
        return contains(x, root);
    }

    private boolean contains(T x, AVLNode<T> n){
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

    private AVLNode<T> findMin(AVLNode<T> n) {
        AVLNode<T> current = n;
        while(current.left != null){
            current = current.left;
        }
        return current;
    }

    @Override
    public T findMax() {
        return findMax(root).element;
    }

    private AVLNode<T> findMax(AVLNode<T> n) {
        AVLNode<T> current = n;
        while(current.right != null){
            current = current.right;
        }
        return current;
    }



    @Override
    public void insert(T x) {
        root = insert(x, root);
        size++;
        System.out.println("Inserting " + x);
    }

    private AVLNode<T> insert(T x, AVLNode<T> n) {
        if(n == null)
            n = new AVLNode<>(x);
        else if(n.element.compareTo(x) > 0)
            n.left = insert(x, n.left);
        else if(n.element.compareTo(x) < 0)
            n.right = insert(x, n.right);

        return balance(n);
    }

    private AVLNode<T> balance(AVLNode<T> n) {
        //refresh height and balance
        int leftNodeHeight;
        int rightNodeHeight;

        if(n.left == null)
            leftNodeHeight = -1;
        else
            leftNodeHeight = n.left.height;

        if(n.right == null)
            rightNodeHeight = -1;
        else
            rightNodeHeight = n.right.height;

        n.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);

        n.balance = rightNodeHeight - leftNodeHeight;

        //balance
        if (n.balance == -2) {          //unbalanced to the left
            if (n.left.balance <= 0)    //Path unbalanced -> left, left
                return simpleRightRotation(n);
            else                        //Path unbalanced -> left, right
                return leftRightRotation(n);
        } else if (n.balance == 2) {    //unbalanced to the right
            if(n.right.balance >= 0)    //Path unbalanced -> right, right
                return simpleLeftRotation(n);
            else                        //Path unbalanced -> right, left
                return rightLeftRotation(n);
        }

        //tree is balanced
        return n;
    }

    @Override
    public void remove(T x) {

    }

    @Override
    public void printInOrder() {

    }

    private void printPostOrder(AVLNode<T> node) {
        if (node == null)
            return;

        // first recur on left subtree
        printPostOrder(node.left);

        // then recur on right subtree
        printPostOrder(node.right);

        // now deal with the node
        System.out.print(node.element + " ");
    }

    @Override
    public void printPostOrder(){
        printPostOrder(root);
    }

    @Override
    public void printPreOrder() {

    }

    @Override
    public Iterator<T> iterator() {
        return new AVLIteratorPostOrder<>(root, size);
    }


    private AVLNode<T> simpleRightRotation(AVLNode<T> n0){
        System.out.println("Rotacao dir");
        AVLNode<T> n1= n0.left;
        n0.left = n1.right;
        n1.right = n0;
        //n0.altura=Math.max(altura(n0.esq),altura(n0.dir))+1;
        //n1.altura=Math.max(altura(n1.dir),altura(n1.esq))+1;
        //System.out.println("OLALLLA");
        return n1;
    }
    private AVLNode<T> simpleLeftRotation(AVLNode<T> n0){
        System.out.println("Rotacao esq");
        AVLNode<T> n1= n0.right;
        n0.right = n1.left;
        n1.left = n0;
        //n0.altura=Math.max(altura(n0.esq),altura(n0.dir))+1;
        //n1.altura=Math.max(altura(n1.dir),altura(n1.esq))+1;
        return n1;
    }

    private AVLNode<T> rightLeftRotation(AVLNode<T> n0){
        System.out.println("Rotaçao dupla dir esq");
        AVLNode<T> n1 = n0.right;
        n0.right = simpleRightRotation(n1);
        return simpleLeftRotation(n0);
    }

    private AVLNode<T> leftRightRotation(AVLNode<T> n0){
        System.out.println("Rotaçao dupla esq dir");
        AVLNode<T> n1 = n0.left;
        n0.left = simpleLeftRotation(n1);
        return simpleRightRotation(n0);
    }

    //HELPER METHODS NOT IN INTERFACE
    /*
    private boolean isBalanced(AVLNode<T> node) {
        if (node == null) //is balanced
            return true;

        if (getHeight(node) == -1) //not balanced
            return false;

        return true; //is balanced
    }

    private int getHeight(AVLNode<T> node) {
        if (node == null)
            return 0;

        int left = getHeight(node.left);
        int right = getHeight(node.right);

        if (left == -1 || right == -1)
            return -1;

        if (Math.abs(left - right) > 1) {
            return -1;
        }

        return Math.max(left, right) + 1;

    }

    private void populateStack(ArrayStack<AVLNode<T>> st, AVLNode<T> n){
        if(n == null)
            return;
        populateStack(st,n.right);
        st.push(n);
        populateStack(st,n.left);
    }

    private int treeHeight(AVLNode<T> node){
        if(node==null)
            return 0;
        return (1+ Math.max(treeHeight(node.left),treeHeight(node.right)));
    }

    private AVLNode<T> search(T x, AVLNode<T> node){
        if(node != null){
            if(node.element.equals(x)){
                return node;
            } else {
                AVLNode foundNode = search(x, node.left);
                if(foundNode == null) {
                    foundNode = search(x, node.right);
                }
                return foundNode;
            }
        } else {
            return null;
        }
    }

    public AVLNode<T> parent(AVLNode<T> p){
        return parentHelper(root,p);
    }
    private AVLNode<T> parentHelper(AVLNode<T> currentRoot, AVLNode<T> p) {
        if (root.equals(p) || currentRoot == null) {
            return null;
        } else {
            if (currentRoot.left == p || currentRoot.right == p)
                return currentRoot;
            else {
                if (currentRoot.element.compareTo(p.element) < 0) {
                    return parentHelper(currentRoot.right, p);
                } else {
                    return parentHelper(currentRoot.left, p);
                }
            }
        }
    }

*/


    /*if (!isBalanced(root)) {
            ArrayStack<AVLNode<T>> stack = new ArrayStack<>(size);
            populateStack(stack, root);
            AVLNode<T> temp;
            while (!stack.empty()) {
                temp = stack.pop();
                if (!isBalanced(temp)) {
                    System.out.println(temp + " is unbalanced");
                    AVLNode<T> parent = new AVLNode<>(null);
                    boolean right = false;
                    if (size > 3) {
                        if (treeHeight(temp.left) > treeHeight(temp.right)) {
                            parent = temp;
                            temp = temp.left;
                        } else {
                            right = true;
                            parent = temp;
                            temp = temp.right;
                        }
                    }
                    if (temp.left != null) {
                        if (temp.left.left != null) {
                            System.out.println("ROTAÇAO DIR AT " + temp.left);
                            if (right && size > 3) {
                                parent.right = rotacaoDir(temp));
                            }
                            else if (!right && size > 3) {
                                parent.left = rotacaoDir(temp));
                            }
                            else {
                                root = rotacaoDir(temp);
                            }
                            break;
                        } else if (temp.left.right != null) {
                            System.out.println("ROTAÇÃO ESQ DIR AT " + temp.left);
                            break;
                        }
                    }
                    if (temp.right != null) {
                        if (temp.right.right != null) {
                            System.out.println("ROTAÇÂO ESQ AT " + temp.right);
                            if (right && size > 3) {
                                System.out.println("Parent: " + parent);
                                parent.right = rotacaoEsq(temp));
                            }else if (!right && size > 3)
                                parent.left = rotacaoEsq(temp));
                            else
                                root = rotacaoEsq(temp);
                            break;
                        } else {
                            System.out.println("ROTAÇÃO DIR ESQ AT " + temp.right);
                            break;
                        }
                    }

                }
            }
        }*/
}

