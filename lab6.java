package classhomeandlabs2024;
/*
 * chase mccrracken
 */

import java.util.Scanner;

class SortNode {
    String data;
    int sortCount;
    SortNode next;

    SortNode(String data) {
        this.data = data;
        this.sortCount = 0;
        this.next = null;
    }
}

class CustomLinkedList {
    SortNode head;

    void append(String data) {
        if (head == null) {
            head = new SortNode(data);
        } else {
            SortNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new SortNode(data);
        }
    }
}

public class lab6 {
    static Scanner scanner = new Scanner(System.in);

    static int countSort(String string) {
        return string.toLowerCase().split("sort", -1).length - 1;
    }

    static CustomLinkedList insertionSortLinkedList(CustomLinkedList list) {
        if (list.head == null || list.head.next == null) {
            return list;
        }

        CustomLinkedList newList = new CustomLinkedList();
        SortNode current = list.head;
        while (current != null) {
            SortNode nextNode = current.next;
            sortedInsert(newList, current);
            current = nextNode;
        }

        return newList;
    }

    static void sortedInsert(CustomLinkedList list, SortNode newNode) {
        if (list.head == null || list.head.sortCount >= newNode.sortCount) {
            newNode.next = list.head;
            list.head = newNode;
        } else {
            SortNode current = list.head;
            while (current.next != null && current.next.sortCount < newNode.sortCount) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    public static void main(String[] args) {
        System.out.println("Enter any number of strings I will sort when done enter 'quit'.\n");

        while (true) {
            CustomLinkedList strings = new CustomLinkedList();

            // Entering data
            while (true) {
                System.out.print("string here (press enter to finish): ");
                String userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("quit")) {
                    break;
                }
                strings.append(userInput);
            }

            // Counting "sort"
            SortNode current = strings.head;
            while (current != null) {
                current.sortCount = countSort(current.data);
                current = current.next;
            }

            // Sorting "sort"
            CustomLinkedList sortedStrings = insertionSortLinkedList(strings);

            // Printing results
            System.out.println("\nSorted the strings:");
            current = sortedStrings.head;
            while (current != null) {
                System.out.println(current.data);
                current = current.next;
            }

            // Asking if the user wants to sort more strings
            System.out.print("\nwould you like to sort more (yes/no): ");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("yes")) {
                break;
            }
        }

        System.out.println("\n bye!");
    }
}
