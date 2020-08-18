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
    String input = "";
    List<Task> list = new ArrayList<>();
    String output;

    System.out.println("\n" + logo);
    System.out.println("\t" + divider);
    System.out.println("\t" + "Hello! I'm Duke\n\tWhat can I do for you?");
    System.out.println("\t" + divider);

    while (!input.equals("bye")) {
      output = "";
      input = sc.nextLine();
      String[] command = input.split(" ", 2);

      if (input.equals("bye")) {
        output = "\tBye. Hope to see you again soon!";
      } else if (input.equals("list")) {
        StringBuilder concat = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
          concat.append(String.format("\n\t%d. %s", i + 1, list.get(i)));
        }

        output = "\tHere are the tasks in your list: " + concat;
      } else if (command[0].equals("done")){
        int index = Integer.parseInt(command[1]) - 1;
        Task targetTask = list.get(index);

        targetTask.setDone();
        output = "\tNice! I've marked this task as done: \n\t\t" + targetTask;
      } else if (command[0].equals("todo")) {
        if (command.length < 2) {
          output = "\t☹ OOPS!!! The description of a todo cannot be empty.";
        } else {
          ToDo newToDo = new ToDo(command[1]);

          list.add(newToDo);
          output = "\tGot it. I've added this task: \n\t\t" + newToDo + "\n\tNow you have " + list.size() + " tasks in the list.";
        }
      } else if (command[0].equals("deadline")) {
        String[] commandParam = command[1].split("/by");
        String description = commandParam[0].trim();
        String by = commandParam[1].trim();
        Deadline newDeadline = new Deadline(description, by);

        list.add(newDeadline);
        output = "\tGot it. I've added this task: \n\t\t" + newDeadline + "\n\tNow you have " + list.size() + " tasks in the list.";
      } else if (command[0].equals("event")) {
        String[] commandParam = command[1].split("/at");
        String description = commandParam[0].trim();
        String at = commandParam[1].trim();
        Event newEvent = new Event(description, at);

        list.add(newEvent);
        output = "\tGot it. I've added this task: \n\t\t" + newEvent + "\n\tNow you have " + list.size() + " tasks in the list.";
      } else if (command[0].equals("delete")) {
        int index = Integer.parseInt(command[1]) - 1;
        Task targetTask = list.remove(index);

        output = "\tNoted. I've removed this task: \n\t\t" + targetTask + "\n\tNow you have " + list.size() + " tasks in the list.";
      } else {
        output = "\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
      }
      System.out.println("\t" + divider);
      System.out.println(output);
      System.out.println("\t" + divider + "\n");
    }


  }
}
