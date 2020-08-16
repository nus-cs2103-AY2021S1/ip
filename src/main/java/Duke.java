import java.util.Scanner;

public class Duke {
  public static void main(String[] args) throws Exception {
    String logo =
        " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println(logo + "\nHello ! I'm Duke\nWhat can I do for you?\n");

    // Arraylist used to ensure scalability (Edited out to fit level-4 requirement)
    //    ArrayList<Task> dataArrayList = new ArrayList<>();
    Task[] tasksArr = new Task[100];
    int taskTracker = 0;
    Scanner scanner = new Scanner(System.in);
    String input = "";
    while (!input.equals("bye")) {
      input = scanner.next();
      // Switch case use to prepare for future requirements
      label:
      switch (input) {
        case "list":
          /*
          // Implementation code using Arraylist (Edited out to fit level-4 requirement)
                   int count = 1;
                   for (Task task : dataArrayList) {
                     System.out.printf("%d.[%s] %s\n", count, task.getStatusIcon(), task);
                     count++;
                   }
          */

          System.out.println("Here are the tasks in your list: ");
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
          // Implementation code using Arraylist (Edited out to fit level-4 requirement)
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
            String addMessage = "Got it. I've added this task:";
            switch (input) {
              case "todo":
                // Change line below to for Arraylist implementation
                String descToDo = scanner.nextLine().trim();
                if (descToDo.equals("")) {
                  throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                System.out.println(addMessage);
                tasksArr[taskTracker] = new Todo(descToDo);
                break;
              case "deadline":
                {
                  scanner.useDelimiter("/by");
                  String descDeadline = scanner.next().trim();
                  if (descDeadline.equals("")) {
                    throw new DukeException(
                        "☹ OOPS!!! The description of a deadline cannot be empty.");
                  }
                  scanner.skip("/by");
                  String by = scanner.nextLine().trim();
                  if (by.equals("")) {
                    throw new DukeException("☹ OOPS!!! The deadline cannot be empty.");
                  }
                  System.out.println(addMessage);
                  // Change line below to for Arraylist implementation
                  tasksArr[taskTracker] = new Deadline(descDeadline, by);
                  break;
                }
              case "event":
                {
                  System.out.println(addMessage);
                  scanner.useDelimiter("/at");
                  String descEvent = scanner.next().trim();
                  if (descEvent.equals("")) {
                    throw new DukeException(
                        "☹ OOPS!!! The description of a event cannot be empty.");
                  }
                  scanner.skip("/at");
                  String at = scanner.nextLine().trim();
                  if (at.equals("")) {
                    throw new DukeException("☹ OOPS!!! The event date cannot be empty.");
                  }
                  // Change line below to for Arraylist implementation
                  tasksArr[taskTracker] = new Event(descEvent, at);
                  break;
                }
              default:
                throw new Exception("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println("\t" + tasksArr[taskTracker]);
            taskTracker++;
            scanner.reset();
            System.out.printf("Now you have %o tasks in list.\n", taskTracker);
          }
          break;
      }
    }
    System.out.println("Bye. Hope to see you again soon!");
  }
}
