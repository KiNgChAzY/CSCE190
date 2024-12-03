package classhomeandlabs2024;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class RobotSimulationn {

    static class GenericQueue<T> {
        private static class Node<T> {
            T data;
            Node<T> next;

            Node(T data) {
                this.data = data;
                this.next = null;
            }
        }

        private Node<T> front;
        private Node<T> rear;

        public GenericQueue() {
            this.front = null;
            this.rear = null;
        }

        public void enqueue(T data) {
            Node<T> newNode = new Node<>(data);
            if (isEmpty()) {
                front = newNode;
                rear = newNode;
            } else {
                rear.next = newNode;
                rear = newNode;
            }
        }

        public T dequeue() {
            if (isEmpty()) {
                throw new IllegalStateException("Queue is empty");
            }
            T data = front.data;
            front = front.next;
            if (front == null) {
                rear = null;
            }
            return data;
        }

        public T peek() {
            if (isEmpty()) {
                throw new IllegalStateException("Queue is empty");
            }
            return front.data;
        }

        public boolean isEmpty() {
            return front == null;
        }
    }

    static class RobotSimulator {
        private static final char EMPTY_SPACE = '_';
        private static final char OBSTACLE = 'X';
        private static final char ROBOT = 'R';

        private char[][] board;
        private int robotRow;
        private int robotCol;

        private GenericQueue<String> robotCommands;

        public RobotSimulator() {
            this.board = null;
            this.robotRow = -1;
            this.robotCol = -1;
            this.robotCommands = new GenericQueue<>();
        }

        public void readBoardFile(String filename) {
            try (Scanner scanner = new Scanner(new File(filename))) {
                int row = 0;
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    if (board == null) {
                        board = new char[line.length()][line.length()];
                    }

                    for (int col = 0; col < line.length(); col++) {
                        board[row][col] = line.charAt(col);
                        if (board[row][col] == ROBOT) {
                            robotRow = row;
                            robotCol = col;
                        }
                    }

                    row++;
                }
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + filename);
                System.exit(1);
            }
        }

        public void readRobotCommandFile(String filename) {
            try (Scanner scanner = new Scanner(new File(filename))) {
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    if (isValidCommand(line)) {
                        robotCommands.enqueue(line);
                    }
                }
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + filename);
                System.exit(1);
            }
        }

        private boolean isValidCommand(String command) {
            return command.equals("Move Up") || command.equals("Move Down") ||
                    command.equals("Move Left") || command.equals("Move Right");
        }

        public void simulateCommands() {
            Scanner scanner = new Scanner(System.in);
            while (!robotCommands.isEmpty()) {
                String command = robotCommands.dequeue();
                simulateCommand(command);

                printBoard();
                System.out.println("Robot's current position: (" + robotRow + ", " + robotCol + ")");

                if (hasCrashed() || isOutOfBounds()) {
                    System.out.println("CRASH");
                    break;
                }

                System.out.print("Press Enter to continue or type 'q' to quit: ");
                String input = scanner.nextLine().trim();
                if (input.equals("q")) {
                    break;
                }
            }
        }

        private void simulateCommand(String command) {
            switch (command) {
                case "Move Up":
                    // Implement the logic to move the robot up
                    break;
                case "Move Down":
                    // Implement the logic to move the robot down
                    break;
                case "Move Left":
                    // Implement the logic to move the robot left
                    break;
                case "Move Right":
                    // Implement the logic to move the robot right
                    break;
            }
        }

        private boolean hasCrashed() {
            return board[robotRow][robotCol] == OBSTACLE;
        }

        private boolean isOutOfBounds() {
            return robotRow < 0 || robotRow >= board.length || robotCol < 0 || robotCol >= board[0].length;
        }

        private void printBoard() {
            for (char[] row : board) {
                for (char cell : row) {
                    System.out.print(cell + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        RobotSimulator robotSimulator = new RobotSimulator();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the filename for the board: ");
        String boardFilename = scanner.nextLine().trim();
        robotSimulator.readBoardFile(boardFilename);

        System.out.print("Enter the filename for the robot commands: ");
        String commandFilename = scanner.nextLine().trim();
        robotSimulator.readRobotCommandFile(commandFilename);

        robotSimulator.simulateCommands();
    }
}
