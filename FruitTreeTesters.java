package classhomeandlabs2024;
/*
 * chase mccracken
 */
import java.util.Scanner;

class Fruit implements Comparable<Fruit> {
    private String type;
    private double weight;

    public Fruit() {
        this.type = "apple";
        this.weight = 1.0;
    }

    
    public Fruit(String type, double weight) {
        this.type = type;
        if (weight > 0)
            this.weight = weight;
        else
            this.weight = 1.0; // Setting default value if weight is invalid
    }

    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight > 0)
            this.weight = weight;
    }

    // toString method
    public String toString() {
        return "Type: " + type + " Weight: " + weight;
    }

    // compareTo method
    public int compareTo(Fruit other) {
        if (this.weight > other.weight)
            return 1;
        else if (this.weight < other.weight)
            return -1;
        else
            return this.type.compareTo(other.type);
    }
}

class Node<T extends Comparable<T>> {
    T data;
    Node<T> leftChild;
    Node<T> rightChild;

    public Node(T data) {
        this.data = data;
        this.leftChild = null;
        this.rightChild = null;
    }
}

class LinkeddBST<T extends Comparable<T>> {
    private Node<T> root;

    public LinkeddBST() {
        root = null;
    }

    public void add(T data) {
        root = add(root, data);
    }

    private Node<T> add(Node<T> node, T data) {
        if (node == null)
            return new Node<>(data);
        if (data.compareTo(node.data) < 0)
            node.leftChild = add(node.leftChild, data);
        else if (data.compareTo(node.data) > 0)
            node.rightChild = add(node.rightChild, data);
        return node;
    }

    public boolean search(T data) {
        return search(root, data);
    }

    private boolean search(Node<T> node, T data) {
        if (node == null)
            return false;
        if (node.data.equals(data))
            return true;
        if (data.compareTo(node.data) < 0)
            return search(node.leftChild, data);
        else
            return search(node.rightChild, data);
    }

    public void printPreOrder() {
        printPreOrder(root);
    }

    private void printPreOrder(Node<T> node) {
        if (node != null) {
            System.out.println(node.data);
            printPreOrder(node.leftChild);
            printPreOrder(node.rightChild);
        }
    }

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(Node<T> node) {
        if (node != null) {
            printInOrder(node.leftChild);
            System.out.println(node.data);
            printInOrder(node.rightChild);
        }
    }

    public void printPostOrder() {
        printPostOrder(root);
    }

    private void printPostOrder(Node<T> node) {
        if (node != null) {
            printPostOrder(node.leftChild);
            printPostOrder(node.rightChild);
            System.out.println(node.data);
        }
    }
}

public class FruitTreeTesters {
    public static void main(String[] args) {
        LinkedBST<Fruit> fruitTree = new LinkedBST<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the fruit tree!");
        System.out.println("Please enter a Fruit File Name:");
        String fileName = scanner.nextLine();

        // File reading and tree population code can be here
    }
}