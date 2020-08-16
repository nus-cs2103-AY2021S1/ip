import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
  public static void main(String[] args) {
    String logo =
        " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println(logo + "\nHello ! I'm Duke\nWhat can I do for you?\n");

    // Arraylist used to ensure scalability
    ArrayList<Task> dataArrayList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    String input = "";
    while (!input.equals("bye")) {
      input = scanner.next();
      // Switch case use to prepare for future requirements
      switch (input) {
        case "list":
          int count = 1;
          for (Task task : dataArrayList) {
            System.out.printf("%d.[%s] %s\n", count, task.getStatusIcon(), task.description);
            count++;
          }
          break;
        case "done":
          int change = scanner.nextInt();
          Task task = dataArrayList.get(change);
          task.markAsDone();
          System.out.println("Nice! I've marked this task as done:");
          System.out.printf("[%s] %s%n", task.getStatusIcon(), task.description);
          break;
        default:
          if (!input.equals("bye")) {
            Task addTask = new Task(input);
            dataArrayList.add(addTask);
            System.out.println("added: " + input);
          }
          break;
      }
    }
    System.out.println("Bye. Hope to see you again soon!");
  }
}
