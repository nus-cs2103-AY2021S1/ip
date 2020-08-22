import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
  
  public static String process(String input, List<Task> list) throws DukeException {
    String output;
    String[] command = input.split(" ", 2);

    if (input.equals("bye")) {
      output = "\tBye. Hope to see you again soon!";
    } else if (input.equals("list")) {
      if (list.isEmpty()) {
        throw new DukeException("\t☹ OOPS!!! There is no task in the list.");
      }
      StringBuilder concat = new StringBuilder();

      for (int i = 0; i < list.size(); i++) {
        concat.append(String.format("\n\t%d. %s", i + 1, list.get(i)));
      }

      output = "\tHere are the tasks in your list: " + concat;
    } else if (command[0].equals("done")){
      int inputNumber;
      
      if (command.length < 2) {
        throw new DukeException("\t☹ OOPS!!! The description of a done cannot be empty.");
      }
      
      try {
        inputNumber = Integer.parseInt(command[1]);
      } catch(NumberFormatException e) {
        throw new DukeException("\t☹ OOPS!!! Argument must be an integer.");
      }
      
      if (inputNumber <= 0) {
        throw new DukeException(("\t☹ OOPS!!! Invalid argument."));
      }
      
      if (inputNumber > list.size()) {
        throw new DukeException("\t☹ OOPS!!! There is only " + list.size() + " tasks in the list.");
      }
      
      int index = inputNumber - 1;
      Task targetTask = list.get(index);

      if (targetTask.getStatus()) {
        throw new DukeException("\t☹ OOPS!!! You've already done that task.");
      }
      
      targetTask.setDone();
      output = "\tNice! I've marked this task as done: \n\t\t" + targetTask;
    } else if (command[0].equals("todo")) {
      if (command.length < 2 || command[1].isBlank()) {
        throw new DukeException("\t☹ OOPS!!! The description of a todo cannot be empty.");
      } 
      
      ToDo newToDo = new ToDo(command[1]);

      list.add(newToDo);
      output = "\tGot it. I've added this task: \n\t\t" + newToDo + "\n\tNow you have " + list.size() + " tasks in the list.";
      
    } else if (command[0].equals("deadline")) {
      if (command.length < 2) {
        throw new DukeException("\t☹ OOPS!!! The description of a deadline cannot be empty.");
      }
      
      String[] commandParam = command[1].split("/by");
      
      if (commandParam.length < 2) {
        throw new DukeException("\t☹ OOPS!!! Invalid Argument (\"/by\" String not found)");
      }
      
      String description = commandParam[0].trim();
      String by = commandParam[1].trim();
      
      if (description.isBlank()) {
        throw new DukeException("\t☹ OOPS!!! The description of a deadline cannot be empty.");
      }
      
      if (by.isBlank()) {
        throw new DukeException("\t☹ OOPS!!! The /by Date of a deadline cannot be empty.");
      }
      
      Deadline newDeadline = new Deadline(description, by);

      list.add(newDeadline);
      output = "\tGot it. I've added this task: \n\t\t" + newDeadline + "\n\tNow you have " + list.size() + " tasks in the list.";
    } else if (command[0].equals("event")) {
      if (command.length < 2) {
        throw new DukeException("\t☹ OOPS!!! The description of a event cannot be empty.");
      }
      
      String[] commandParam = command[1].split("/at");
      String description = commandParam[0].trim();
      String at = commandParam[1].trim();
      Event newEvent = new Event(description, at);

      list.add(newEvent);
      output = "\tGot it. I've added this task: \n\t\t" + newEvent + "\n\tNow you have " + list.size() + " tasks in the list.";
    } else if (command[0].equals("delete")) {
      if (command.length < 2) {
        throw new DukeException("\t☹ OOPS!!! The description of a delete cannot be empty.");
      }
      
      int index = Integer.parseInt(command[1]) - 1;
      Task targetTask = list.remove(index);

      output = "\tNoted. I've removed this task: \n\t\t" + targetTask + "\n\tNow you have " + list.size() + " tasks in the list.";
    } else {
      throw new DukeException("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    return output;
  }

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
      input = sc.nextLine();

      try {
        output = process(input, list);
      } catch (DukeException e) {
        output = e.getMessage();
      }

      System.out.println("\t" + divider);
      System.out.println(output);
      System.out.println("\t" + divider + "\n");
    }
  }
}
