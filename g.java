// chase mccracken
package classhomeandlabs2024;
//Add this method to the GenLL class
public Node<T> getHead() {
 return head;
}

//Modify the printTasksToFile method in the TaskManager class
public void printTasksToFile(String fileName) {
 try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
     for (int i = organizedTasks.length - 1; i >= 0; i--) {
         GenLL<Task> list = organizedTasks[i];
         Node<Task> current = list.getHead();  // Use the getter method
         while (current != null) {
             writer.write(i + "\t" + current.data.action + "\n");
             current = current.next;
         }
     }
     System.out.println("Tasks written to file successfully.");
 } catch (IOException e) {
     System.out.println("Error writing tasks to file.");
 }
}