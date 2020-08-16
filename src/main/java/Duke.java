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

    // Arraylist used to ensure scalability (Edited out to fix level-4 requirement)
    //    ArrayList<Task> dataArrayList = new ArrayList<>();
    Task[] tasksArr = new Task[100];
    int taskTracker = 0;
    Scanner scanner = new Scanner(System.in);
    String input = "";
    while (!input.equals("bye")) {
      input = scanner.next();
      // Switch case use to prepare for future requirements
      switch (input) {
        case "list":
          /*
          // Implementation code using Arraylist (Edited out to fix level-4 requirement)
                   int count = 1;
                   for (Task task : dataArrayList) {
                     System.out.printf("%d.[%s] %s\n", count, task.getStatusIcon(), task);
                     count++;
                   }
          */

          for (int i = 0; i < tasksArr.length; i++) {
            if (tasksArr[i] == null) {
              break;
            }
            System.out.printf("%d.%s\n", i + 1, tasksArr[i]);
          }
          break;
        case "done":
          int change = scanner.nextInt() - 1;
          /*
          // Implementation code using Arraylist (Edited out to fix level-4 requirement)
                    Task task = dataArrayList.get(change);
                    task.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("%s\n", task);
          */
          tasksArr[change].markAsDone();
          System.out.println("Nice! I've marked this task as done:");
          System.out.printf("%s\n", tasksArr[change]);
          break;
        default:
          if (!input.equals("bye")) {
            // Implementation code using Arraylist (Edited out to fix level-4 requirement)
            // dataArrayList.add(new Task(input));
            tasksArr[taskTracker] = new Task(input);
            taskTracker++;
            System.out.println("added: " + input);
          }
          break;
      }
    }
    System.out.println("Bye. Hope to see you again soon!");
  }
}
