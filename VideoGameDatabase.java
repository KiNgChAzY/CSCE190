package classhomeandlabs2024;
/*
 * Chase McCracken
 */
import java.io.*;
import java.util.Scanner;

class Game {
    String title;
    String console;

    public Game(String title, String console) {
        this.title = title;
        this.console = console;
    }

    @Override
    public String toString() {
        return title + "    " + console;
    }
}

class LinkedList<T> {
    private Node<T> head;

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void clear() {
        head = null;
    }

    public Node<T> getHead() {
        return head;
    }

    static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
}

public class VideoGameDatabase {
    private LinkedList<Game> games = new LinkedList<>();
    private String lastSearchResults = "";

    public static void main(String[] args) {
        VideoGameDatabase database = new VideoGameDatabase();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to THE Video Game Database!\n");

        while (true) {
            System.out.println("Enter 1 to load the video game database");
            System.out.println("Enter 2 to search the database");
            System.out.println("Enter 3 to print current results to the console");
            System.out.println("Enter 4 to print current results to file");
            System.out.println("Enter 0 to quit");

            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    database.loadGameCollection(scanner);
                    break;
                case 2:
                    database.searchGames(scanner);
                    break;
                case 3:
                    database.printResultsToConsole();
                    break;
                case 4:
                    database.printResultsToFile(scanner);
                    break;
                case 0:
                    System.out.println("bye!");
                    return;
                default:
                    System.out.println("Invalid choice");
            }

            System.out.println("\nPress Enter to continue");
            scanner.nextLine(); // Wait for Enter key
        }
    }

    private void loadGameCollection(Scanner scanner) {
        System.out.print("Enter the file name: ");
        String fileName = scanner.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            games.clear();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length == 2) {
                    games.add(new Game(parts[0], parts[1]));
                }
            }
            System.out.println("Game collection loaded");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        } catch (IOException e) {
            System.out.println("Error on: " + e.getMessage());
        }
    }

    private void searchGames(Scanner scanner) {
        if (games.getHead() == null) {
            System.out.println("Game collection is empty load a collection first.");
            return;
        }

        System.out.print("Enter the name of the game or '*' for all ");
        String titleQuery = scanner.nextLine().toLowerCase();
        System.out.print("Enter the name of the console or '*' for all");
        String consoleQuery = scanner.nextLine().toLowerCase();

        StringBuilder results = new StringBuilder();
        LinkedList.Node<Game> current = games.getHead();

        while (current != null) {
            Game game = current.data;
            if ((titleQuery.equals("*") || game.title.toLowerCase().contains(titleQuery))
                    && (consoleQuery.equals("*") || game.console.toLowerCase().contains(consoleQuery))) {
                results.append(game).append("\n");
            }
            current = current.next;
        }

        lastSearchResults = results.toString();
        System.out.println(lastSearchResults.isEmpty() ? "No matching games" : lastSearchResults);
    }

    private void printResultsToConsole() {
        if (lastSearchResults.isEmpty()) {
            System.out.println("Nothing to print.");
        } else {
            System.out.println("results:\n" + lastSearchResults);
        }
    }

    private void printResultsToFile(Scanner scanner) {
        if (lastSearchResults.isEmpty()) {
            System.out.println("No search results to print to file.");
            return;
        }

        System.out.print("Enter the file name  ");
        String fileName = scanner.nextLine();

        System.out.print("Would you like to pick (true/false) ");
        boolean append = scanner.nextBoolean();
        scanner.nextLine(); // Consume newline

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, append))) {
            bw.write(lastSearchResults);
            System.out.println("printed to file:  " + fileName);
        } catch (IOException e) {
            System.out.println("Error  " + e.getMessage());
        }
    }
}
