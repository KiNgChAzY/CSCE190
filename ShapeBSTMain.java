package classhomeandlabs2024;
// chase McCracken

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Shape {

    private String type;
    private double area;
    
    public Shape(String type, double area) {
        this.type = type;
        this.area = area;
    }

    public String getType() {
        return type;
    }

    public double getArea() {
        return area;
    }

    public String toString() {
        return type + " Area: " + area;
    }
}


class TreeNode {
    private Shape shape;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(Shape shape) {
        this.shape = shape;
        left = null;
        right = null;
    }

    public Shape getShape() {
        return shape;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}

class ShapeBST {
    private TreeNode root;

    // Constructor
    public ShapeBST() {
        root = null;
    }

    public void addShape(Shape shape) {
        root = addShapeRecursive(root, shape);
    }

    private TreeNode addShapeRecursive(TreeNode current, Shape shape) {
        if (current == null) {
            return new TreeNode(shape);
        }

        if (shape.getArea() < current.getShape().getArea()) {
            current.setLeft(addShapeRecursive(current.getLeft(), shape));
        } else if (shape.getArea() > current.getShape().getArea()) {
            current.setRight(addShapeRecursive(current.getRight(), shape));
        } else {
           
        }

        return current;
    }
    public void removeShape(String type, double area) {
        root = removeShapeRecursive(root, type, area);
    }

    private TreeNode removeShapeRecursive(TreeNode current, String type, double area) {
        return current;
    }

    public boolean searchShape(String type, double area) {
        return searchShapeRecursive(root, type, area);
    }

    private boolean searchShapeRecursive(TreeNode current, String type, double area) {
        return false;
    }

    // Find the shape with the maximum area
    public Shape findMaxArea() {
        TreeNode maxNode = findMaxAreaRecursive(root);
        return maxNode != null ? maxNode.getShape() : null;
    }

    private TreeNode findMaxAreaRecursive(TreeNode node) {
    	
        return null;
    }

    public void removeShapesGreaterThan(double area) {
        root = removeShapesGreaterThanRecursive(root, area);
    }

    private TreeNode removeShapesGreaterThanRecursive(TreeNode current, double area) {
        return current;
    }

    public void printTreeTraversal(int traversal) {
        switch (traversal) {
            case 1:
                System.out.println("Pre-order traversa");
                printPreOrder(root);
                break;
            case 2:
                System.out.println("In-order traversal");
                printInOrder(root);
                break;
            case 3:
                System.out.println("Post-order traversal");
                printPostOrder(root);
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    private void printPreOrder(TreeNode node) {
        if (node != null) {
            System.out.println(node.getShape());
            printPreOrder(node.getLeft());
            printPreOrder(node.getRight());
        }
    }

    private void printInOrder(TreeNode node) {
        if (node != null) {
            printInOrder(node.getLeft());
            System.out.println(node.getShape());
            printInOrder(node.getRight());
        }
    }

    private void printPostOrder(TreeNode node) {
        if (node != null) {
            printPostOrder(node.getLeft());
            printPostOrder(node.getRight());
            System.out.println(node.getShape());
        }
    }
    public void readShapesFromFile(String fileName) {
        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            while (fileScanner.hasNextLine()) {
                String[] parts = fileScanner.nextLine().split("\t");
                String type = parts[0];
                double area = 0.0;

                switch (type.toLowerCase()) {
                    case "Circle":
                        double radius = Double.parseDouble(parts[1]);
                        area = Math.PI * radius * radius;
                        break;
                    case "Rectangle":
                        double length = Double.parseDouble(parts[1]);
                        double width = Double.parseDouble(parts[2]);
                        area = length * width;
                        break;
                    case "Right triangle":
                        double base = Double.parseDouble(parts[1]);
                        double height = Double.parseDouble(parts[2]);
                        area = 0.5 * base * height;
                        break;
                }

                Shape shape = new Shape(type, area);
                addShape(shape);
            }
            System.out.println("Shapes read from file");
        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }
    }
    public void writeShapesToFile(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writeShapesRecursive(root, writer);
            System.out.println("Shapes written to file");
        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    private void writeShapesRecursive(TreeNode node, FileWriter writer) throws IOException {
        if (node != null) {
            writeShapesRecursive(node.getLeft(), writer);
            writer.write(node.getShape().getType() + "\t" + node.getShape().getArea() + "\n");
            writeShapesRecursive(node.getRight(), writer);
        }
    }

 
}

public class ShapeBSTMain {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ShapeBST shapeTree = new ShapeBST();

        System.out.println("Welcome to the Shape Tree!");

        int choice;
        do {
            System.out.println("\nEnter 1. To Read a Shape Tree from a File.");
            System.out.println("Enter 2. To Print a Tree Traversal to the Console");
            System.out.println("Enter 3. To Add a Shape.");
            System.out.println("Enter 4. To Remove a Shape.");
            System.out.println("Enter 5. To Search for a Shape.");
            System.out.println("Enter 6. To Find the Shape with the Max Area.");
            System.out.println("Enter 7. To Remove All Shapes Greater than an Area.");
            System.out.println("Enter 8. To Print Shape Tree to File.");
            System.out.println("Enter 0. To Quit.");

            choice = scanner.nextInt();
            scanner.nextLine(); // take newline

            switch (choice) {
                case 1:
                    System.out.print("enter file name to read shapes");
                    String fileName = scanner.nextLine();
                    shapeTree.readShapesFromFile(fileName);
                    break;
                case 2:
                    System.out.println("Enter 1. For Pre-order traversal");
                    System.out.println("Enter 2. For In-order traversal");
                    System.out.println("Enter 3. For Post-order traversal");
                    int traversalChoice = scanner.nextInt();
                    shapeTree.printTreeTraversal(traversalChoice);
                    break;
                case 3:
                    // Add a shape
                    System.out.print("enter shape type(Circle/Rectangle/Right Triangle) ");
                    String type = scanner.nextLine();
                    double area = 0.0;

                    switch (type.toLowerCase()) {
                        case "Circle":
                            System.out.print("Enter radius ");
                            double radius = scanner.nextDouble();
                            area = Math.PI * radius * radius;
                            break;
                        case "Rectangle":
                            System.out.print("Enter length ");
                            double length = scanner.nextDouble();
                            System.out.print("Enter width ");
                            double width = scanner.nextDouble();
                            area = length * width;
                            break;
                        case "Right triangle":
                            System.out.print("Enter base ");
                            double base = scanner.nextDouble();
                            System.out.print("Enter height ");
                            double height = scanner.nextDouble();
                            area = 0.5 * base * height;
                            break;
                    }

                    Shape newShape = new Shape(type, area);
                    shapeTree.addShape(newShape);
                    System.out.println("shape added");
                    break;
                case 4:
                    // Remove a shape
                    System.out.print("enter shape type to remove (Circle/Rectangle/Right riangle) ");
                    String shapeType = scanner.nextLine();
                    System.out.print("enter area of shape to remove: ");
                    double shapeArea = scanner.nextDouble();
                    shapeTree.removeShape(shapeType, shapeArea);
                    System.out.println("Shape removed");
                    break;
                case 5:
                    // Search for a shape
                    System.out.print("enter shape type (Circle/Rectangle/Right triangle) ");
                    String searchType = scanner.nextLine();
                    System.out.print("enter area of shape");
                    double searchArea = scanner.nextDouble();
                    boolean found = shapeTree.searchShape(searchType, searchArea);
                    if (found) {
                        System.out.println("Shape found");
                    } else {
                        System.out.println("Shape not found");
                    }
                    break;
                case 6:
                    Shape maxAreaShape = shapeTree.findMaxArea();
                    System.out.println("shapes maximum area: " + maxAreaShape);
                    break;
                case 7:
                    System.out.print("enter the threshold: ");
                    double threshold = scanner.nextDouble();
                    shapeTree.removeShapesGreaterThan(threshold);
                    System.out.println("shapes bigger than " + threshold + " removed");
                    break;
                case 8:
                    System.out.print("enter file name to write shapes ");
                    String writeFile = scanner.nextLine();
                    shapeTree.writeShapesToFile(writeFile);
                    break;
                case 0:
                    System.out.println("\nGoodbye!");
                    break;
                default:
                    System.out.println("Invalid try again.");
            }
        } while (choice != 0);
    }
}
