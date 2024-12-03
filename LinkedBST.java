package classhomeandlabs2024;

public class LinkedBST<T extends Comparable<T>> {
    private class Node {
        T data;
        Node leftChild;
        Node rightChild;

        public Node(T aData) {
            data = aData;
            leftChild = rightChild = null;
        }
    }
    // Node root  
    private Node root;

    public LinkedBST() {
        root = null;
    }

    public void add(T aData) {
        if (root == null)
            root = new Node(aData);
        else
            add(root, aData);
    }
    // Sorting
    private Node add(Node aNode, T aData) {
        if (aNode == null)
            aNode = new Node(aData);
        else if (aData.compareTo(aNode.data) < 0)
            aNode.leftChild = add(aNode.leftChild, aData);
        else if (aData.compareTo(aNode.data) > 0)
            aNode.rightChild = add(aNode.rightChild, aData);
        return aNode;
    }

    public void printInorder() {
        printInorder(root);
    }

    private void printInorder(Node aNode) {
        if (aNode == null)
            return;
        printInorder(aNode.leftChild);
        System.out.println(aNode.data);
        printInorder(aNode.rightChild);
    }

    public boolean contains(T aData) {
        return contains(root, aData);
    }
    
    private boolean contains(Node aNode, T aData) {
        if (aNode == null)
            return false;
        int compareResult = aData.compareTo(aNode.data);
        if (compareResult == 0)
            return true;
        else if (compareResult < 0)
            return contains(aNode.leftChild, aData);
        else
            return contains(aNode.rightChild, aData);
    }
    // Numbers being sorted
    public static void main(String[] args) {
        LinkedBST<Integer> tree = new LinkedBST<>();
        tree.add(8);
        tree.add(2);
        tree.add(12);
        tree.add(1);
        tree.add(4);
        tree.add(10);
        tree.add(16);
        
        // Printing
        System.out.println("Low to high");
        tree.printInorder();

    }
}
