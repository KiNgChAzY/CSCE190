package classhomeandlabs2024;
// chase mccracken

import java.io.*;
import java.util.Scanner;

class Task {
    String action;
    int priority;

    public Task(String action, int priority) {
        this.action = action;
        this.priority = priority;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return priority == task.priority && action.equals(task.action);
    }


    public int hashCode() {
        return 31 * action.hashCode() + priority;
    }

    public String toString() {
        return "Priority " + priority + ": " + action;
    }
}

public class TaskManager {
    private Task[][] organizedTasks;

    public TaskManager() {
        organizedTasks = new Task[5][];
        for (int i = 0; i < organizedTasks.length; i++) {
            organizedTasks[i] = new Task[0];
        }
    }

    public void addTask(String action, int priority) {
        Task task = new Task(action, priority);
        int index = task.priority;
        Task[] list = organizedTasks[index];
        if (!contains(list, task)) {
            organizedTasks[index] = addToArray(list, task);
            System.out.println("task added");
            // see if task add or not
        } else {
            System.out.println("Dup task not added");
        }
    }

    public void removeTask(String action, int priority) {
        Task task = new Task(action, priority);
        int index = task.priority;
        Task[] list = organizedTasks[index];
        organizedTasks[index] = removeFromArray(list, task);
        System.out.println("task removed");
        // removing asked task
    }

    public void printTasks() {
        for (int i = organizedTasks.length - 1; i >= 0; i--) {
            Task[] list = organizedTasks[i];
            System.out.println("priority " + i + ":");
            for (Task task : list) {
                System.out.println(task);
            }
        }
    }

    public void readTasksFromFile(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split("\t");
                if (parts.length == 2) {
                    int priority = Integer.parseInt(parts[0]);
                    String action = parts[1];
                    addTask(action, priority);
                }
            }
            System.out.println("tasks loaded from file");
        } catch (FileNotFoundException | NumberFormatException e) {
            System.out.println("Error reading tasks from file");
        }
    }

    public void printTasksToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (int i = organizedTasks.length - 1; i >= 0; i--) {
                Task[] list = organizedTasks[i];
                for (Task task : list) {
                    writer.write(i + "\t" + task.action + "\n");
                }
            }
            System.out.println("tasks written to file");
        } catch (IOException e) {// seeing if task was written
            System.out.println("Error writing tasks to file");
        }
    }

    private static boolean contains(Task[] array, Task target) {
        for (Task task : array) {
            if (task.equals(target)) {
                return true;
            }
        }
        return false;
    }

    private static Task[] addToArray(Task[] array, Task element) {
        Task[] newArray = new Task[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = element;
        return newArray;
    }

    private static Task[] removeFromArray(Task[] array, Task target) {
        Task[] newArray = new Task[array.length - 1];
        int newIndex = 0;
        for (Task task : array) {
            if (!task.equals(target)) {
                newArray[newIndex++] = task;
            }
        }
        return newArray;
    }

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nTask Manager Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. Print Tasks");
            System.out.println("4. Read Tasks from File");
            System.out.println("5. Print Tasks to File");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {//choices
                case 1:
                    System.out.print("Enter task action: ");
                    String action = scanner.nextLine();
                    System.out.print("Enter task priority (0 to 4): ");
                    int priority = scanner.nextInt();
                    taskManager.addTask(action, priority);
                    break;
                case 2:
                    System.out.print("Enter task action: ");
                    action = scanner.nextLine();
                    System.out.print("Enter task priority (0 to 4): ");
                    priority = scanner.nextInt();
                    taskManager.removeTask(action, priority);
                    break;
                case 3:
                    taskManager.printTasks();
                    break;
                case 4:
                    System.out.print("Enter file name: ");
                    String fileName = scanner.nextLine();
                    taskManager.readTasksFromFile(fileName);
                    break;
                case 5:
                    System.out.print("Enter file name: ");
                    fileName = scanner.nextLine();
                    taskManager.printTasksToFile(fileName);
                    break;
                case 6:
                    System.out.println("Exiting Task Manager. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}