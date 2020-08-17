import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
  public static void main(String[] args) {
    String divider = "---------------------------------------------------------------------------------------------";
    String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
    Scanner sc = new Scanner(System.in);
    String input;
    List<Task> list = new ArrayList<>();

    System.out.println("\n" + logo);
    System.out.println("\t" + divider);
    System.out.println("\t" + "Hello! I'm Duke\n\tWhat can I do for you?");
    System.out.println("\t" + divider);

    while (true) {
      input = sc.nextLine();
      String[] command = input.split(" ", 2);

      if (input.equals("bye")) {
        break;
      } else if (input.equals("list")) {
        System.out.println("\t" + divider);
        System.out.println("\tHere are the tasks in your list: ");

        for (int i = 0; i < list.size(); i++) {
          System.out.printf("\t%d. %s\n", i + 1, list.get(i));
        }

        System.out.println("\t" + divider + "\n");
      } else if (command[0].equals("done")){
        int index = Integer.parseInt(command[1]) - 1;
        Task targetTask = list.get(index);
        targetTask.setDone();

        System.out.println("\t" + divider);
        System.out.println("\tNice! I've marked this task as done: ");
        System.out.println("\t\t" + targetTask);
        System.out.println("\t" + divider);
      } else if (command[0].equals("todo")) {
        if (command.length < 2) {
          System.out.println("\t" + divider);
          System.out.println("\t☹ OOPS!!! The description of a todo cannot be empty.");
          System.out.println("\t" + divider + "\n");
        } else {
          ToDo newToDo = new ToDo(command[1]);
          list.add(newToDo);

          System.out.println("\t" + divider);
          System.out.println("\tGot it. I've added this task: ");
          System.out.println("\t\t" + newToDo);
          System.out.println("\tNow you have " + list.size() + " tasks in the list.");
          System.out.println("\t" + divider);
        }
      } else if (command[0].equals("deadline")) {
        String[] commandParam = command[1].split("/by");
        String description = commandParam[0].trim();
        String by = commandParam[1].trim();
        Deadline newDeadline = new Deadline(description, by);
        list.add(newDeadline);

        System.out.println("\t" + divider);
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t\t" + newDeadline);
        System.out.println("\tNow you have " + list.size() + " tasks in the list.");
        System.out.println("\t" + divider);
      } else if (command[0].equals("event")) {
        String[] commandParam = command[1].split("/at");
        String description = commandParam[0].trim();
        String at = commandParam[1].trim();
        Event newEvent = new Event(description, at);
        list.add(newEvent);

        System.out.println("\t" + divider);
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t\t" + newEvent);
        System.out.println("\tNow you have " + list.size() + " tasks in the list.");
        System.out.println("\t" + divider);
      } else if (command[0].equals("delete")) {
        int index = Integer.parseInt(command[1]) - 1;
        Task targetTask = list.remove(index);

        System.out.println("\t" + divider);
        System.out.println("\tNoted. I've removed this task: ");
        System.out.println("\t\t" + targetTask);
        System.out.println("\tNow you have " + list.size() + " tasks in the list.");
        System.out.println("\t" + divider);
      } else {
        System.out.println("\t" + divider);
        System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println("\t" + divider + "\n");
      }
    }

    System.out.println("\t" + divider);
    System.out.println("\tBye. Hope to see you again soon!");
    System.out.println("\t" + divider);
  }
}
