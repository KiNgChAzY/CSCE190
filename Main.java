/*
 * chase mccracken
 */
package classhomeandlabs2024;
import java.util.Scanner;

// Process class
class Process {
    private String name;
    private double completionTime;

    public Process() {
        this.name = "none";
        this.completionTime = 0.0;
    }

    public Process(String name, double completionTime) {
        this.name = name;
        setCompletionTime(completionTime);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
    }

    public double getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(double completionTime) {
        if (completionTime >= 0.0) {
            this.completionTime = completionTime;
        }
    }

    public String toString() {
        return "Process Name: " + name + " Completion Time: " + completionTime;
    }
}

// Queue interface
interface QueueI<T> {
    void enqueue(T data);

    T dequeue();

    T peek();

    void print();
}

// Linked List Queue class
class LLQueue<T> implements QueueI<T> {
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> head;
    private Node<T> tail;

    public LLQueue() {
        this.head = null;
        this.tail = null;
    }

    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T data = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return data;
    }

 
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return head.data;
    }

    public void print() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public boolean isEmpty() {
        return head == null;
    }
}

// Process Scheduler class
class ProcessScheduler {
    private LLQueue<Process> processQueue;
    private Process currentProcess;

    public ProcessScheduler() {
        this.processQueue = new LLQueue<>();
        this.currentProcess = null;
    }

    public Process getCurrentProcess() {
        return currentProcess;
    }

    public void addProcess(Process process) {
        if (currentProcess == null) {
            currentProcess = process;
        } else {
            processQueue.enqueue(process);
        }
    }

    public void runNextProcess() {
        if (!processQueue.isEmpty()) {
            currentProcess = processQueue.dequeue();
        }
    }

    public void cancelCurrentProcess() {
        if (currentProcess != null) {
            processQueue.enqueue(currentProcess);
            currentProcess = null;
        }
    }

    public void printProcessQueue() {
        processQueue.print();
    }
}

public class Main {
    public static void main(String[] args) {
        ProcessScheduler scheduler = new ProcessScheduler();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Process\n2. Run Next Process\n3. Cancel Current Process\n4. Print Process Queue\n5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter process name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter completion time: ");
                    double completionTime = scanner.nextDouble();
                    Process newProcess = new Process(name, completionTime);
                    scheduler.addProcess(newProcess);
                    break;
                case 2:
                    scheduler.runNextProcess();
                    System.out.println("Running next process: " + scheduler.getCurrentProcess());
                    break;
                case 3:
                    scheduler.cancelCurrentProcess();
                    System.out.println("Current process cancelled.");
                    break;
                case 4:
                    System.out.println("Process Queue: ");
                    scheduler.printProcessQueue();
                    break;
                case 5:
                    System.out.println("Exiting program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
