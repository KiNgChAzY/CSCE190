//chase mccracken
package classhomeandlabs2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Sheep implements Comparable<Sheep> {
    String name;
    int shearingTime;
    int arrivalTime;

    public Sheep(String name, int shearingTime, int arrivalTime) {
        this.name = name;
        this.shearingTime = shearingTime;
        this.arrivalTime = arrivalTime;
    }

    public int compareTo(Sheep other) {
        if (this.shearingTime != other.shearingTime) {
            return Integer.compare(this.shearingTime, other.shearingTime);
        }
        return this.name.compareTo(other.name);
    }

    public String toString() {
        return "Name: " + name + ", Shearing Time: " + shearingTime + ", Arrival Time: " + arrivalTime;
    }
}

class MinHeapp<T extends Comparable<T>> {
    private Object[] heap;
    private int size;
    private static final int CAPACITY = 10;

    public MinHeapp() {
        heap = new Object[CAPACITY];
        size = 0;
    }

    public void add(T element) {
        ensureCapacity();
        heap[size] = element;
        size++;
        heapifyUp(size - 1);
    }

    @SuppressWarnings("unchecked")
    public T remove() {
        if (isEmpty())
            throw new IllegalStateException("Heap is empty");
        T removedItem = (T) heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
        return removedItem;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void heapifyUp(int index) {
        int parentIndex = (index - 1) / 2;
        while (index > 0 && ((T) heap[parentIndex]).compareTo((T) heap[index]) > 0) {
            swap(parentIndex, index);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    private void heapifyDown(int index) {
        int leftChildIndex;
        int rightChildIndex;
        int smallerChildIndex;

        while (index < size / 2) {
            leftChildIndex = 2 * index + 1;
            rightChildIndex = 2 * index + 2;
            smallerChildIndex = leftChildIndex;

            if (rightChildIndex < size && ((T) heap[rightChildIndex]).compareTo((T) heap[leftChildIndex]) < 0) {
                smallerChildIndex = rightChildIndex;
            }

            if (((T) heap[index]).compareTo((T) heap[smallerChildIndex]) <= 0) {
                break;
            }

            swap(index, smallerChildIndex);
            index = smallerChildIndex;
        }
    }

    private void swap(int index1, int index2) {
        Object temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    private void ensureCapacity() {
        if (size == heap.length) {
            heap = java.util.Arrays.copyOf(heap, heap.length * 2);
        }
    }
}

// main class
public class SheepShearingSchedule {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file name: ");
        String fileName = scanner.nextLine();

        // reading sheep data from file
        Sheep[] sheepArray = readSheepDataFromFile(fileName);
//create min heap
        MinHeap<Sheep> sheepHeap = new MinHeap<>();

        int currentTime = 0;
        int totalSheared = 0;
        int totalShearingTime = 0;

        while (totalSheared < sheepArray.length) {
            for (Sheep sheep : sheepArray) {
                if (sheep.arrivalTime == currentTime) {
                    sheepHeap.add(sheep);
                }
            }

            if (!sheepHeap.isEmpty()) {
                Sheep currentSheep = sheepHeap.remove();
                totalSheared++;
                totalShearingTime += currentSheep.shearingTime;
                System.out.println("Shearing " + currentSheep.toString() + " at time " + currentTime);
            }

            currentTime++;
        }

        System.out.println("Total shearing time: " + totalShearingTime);
        scanner.close();
    }

    // reading data from file
    private static Sheep[] readSheepDataFromFile(String fileName) {
        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            int count = 0;
            while (fileScanner.hasNextLine()) {
                fileScanner.nextLine();
                count++;
            }
            fileScanner.close();

            Sheep[] sheepArray = new Sheep[count];
            Scanner scanner = new Scanner(new File(fileName));
            int index = 0;
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split("\t");
                sheepArray[index] = new Sheep(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                index++;
            }
            scanner.close();
            return sheepArray;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            return new Sheep[0];
        }
    }
}
