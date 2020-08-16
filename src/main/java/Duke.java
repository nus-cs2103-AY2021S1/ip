import java.util.ArrayList;
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

    ArrayList<Task> dataArrayList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    String input = "";
    while (!input.equals("bye")) {
      input = scanner.next();
      // Switch case use for scalability
      switch (input) {
        case "list":
          System.out.println("Here are the tasks in your list: ");
          int count = 1;
          for (Task task : dataArrayList) {
            System.out.printf("%d.%s\n", count, task);
            count++;
          }
          break;
        case "done":
          int change = scanner.nextInt() - 1;
          Task task = dataArrayList.get(change);
          task.markAsDone();
          System.out.println("Nice! I've marked this task as done:");
          System.out.printf("%s\n", task);
          break;
        default:
          if (!input.equals("bye")) {
            String addMessage = "Got it. I've added this task:";
            switch (input) {
              case "todo":
                String descToDo = scanner.nextLine().trim();
                if (descToDo.equals("")) {
                  throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                System.out.println(addMessage);
                dataArrayList.add(new Todo(descToDo));
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
                  dataArrayList.add(new Deadline(descDeadline, by));
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
                  dataArrayList.add(new Event(descEvent, at));
                  break;
                }
              default:
                throw new Exception("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            int arrListSize = dataArrayList.size();
            System.out.println("\t" + dataArrayList.get(arrListSize - 1));
            scanner.reset();
            System.out.printf("Now you have %o tasks in list.\n", arrListSize);
          }
          break;
      }
    }
    System.out.println("Bye. Hope to see you again soon!");
  }
}
