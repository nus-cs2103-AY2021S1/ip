import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
  public static void updateData(List<Task> list) {
    try {
      FileWriter fw = new FileWriter("data/duke.txt");
      StringBuilder textToAdd = new StringBuilder();
      
      for (Task t : list) {
        String data = t.getData();
        textToAdd.append(data).append("\n");
      }
      
      fw.write(textToAdd.toString());
      fw.close();
    } catch (IOException e) {
      System.out.println("Something went wrong" + e.getMessage());
    }
  }
  
  public static void loadData(List<Task> list) {
    try {
      File file = new File("data/duke.txt");
      
      if (file.exists()) {
        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
          String[] data = sc.nextLine().split("/");
          String taskType = data[0];
          boolean status = data[1].equals("1");
          String description = data[2];
          String additional;

          switch (taskType) {
            case "t":
              list.add(new ToDo(description, status));
              break;
            case "d":
              additional = data[3];
              list.add(new Deadline(description, additional, status));
              break;
            case "e":
              additional = data[3];
              list.add(new Event(description, additional, status));
              break;
          }
        }
      } else {
        File dir = new File("data");
        dir.mkdir();
        file.createNewFile();
      }
      
    } catch (FileNotFoundException e) {
      System.out.println("File not exists" + e.getMessage());
    } catch (IOException e) {
      System.out.println("Something went wrong " + e.getMessage());
    }
  }
  
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
      if (command.length < 2) {
        throw new DukeException("\t☹ OOPS!!! The description of a done cannot be empty.");
      }

      int inputNumber;
      
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
      updateData(list);
      output = "\tNice! I've marked this task as done: \n\t\t" + targetTask;
    } else if (command[0].equals("todo")) {
      if (command.length < 2 || command[1].isBlank()) {
        throw new DukeException("\t☹ OOPS!!! The description of a todo cannot be empty.");
      } 
      
      ToDo newToDo = new ToDo(command[1]);

      list.add(newToDo);
      updateData(list);
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
        throw new DukeException("\t☹ OOPS!!! The /by description of a deadline cannot be empty.");
      }
      
      Deadline newDeadline = new Deadline(description, by);

      list.add(newDeadline);
      updateData(list);
      output = "\tGot it. I've added this task: \n\t\t" + newDeadline + "\n\tNow you have " + list.size() + " tasks in the list.";
    } else if (command[0].equals("event")) {
      if (command.length < 2) {
        throw new DukeException("\t☹ OOPS!!! The description of a event cannot be empty.");
      }
      
      String[] commandParam = command[1].split("/at");

      if (commandParam.length < 2) {
        throw new DukeException("\t☹ OOPS!!! Invalid Argument (\"/at\" String not found)");
      }
      
      String description = commandParam[0].trim();
      String at = commandParam[1].trim();
      
      if (description.isBlank()) {
        throw new DukeException("\t☹ OOPS!!! The description of a event cannot be empty.");
      }

      if (at.isBlank()) {
        throw new DukeException("\t☹ OOPS!!! The /at description of a event cannot be empty.");
      }
      
      Event newEvent = new Event(description, at);

      list.add(newEvent);
      updateData(list);
      output = "\tGot it. I've added this task: \n\t\t" + newEvent + "\n\tNow you have " + list.size() + " tasks in the list.";
    } else if (command[0].equals("delete")) {
      if (command.length < 2) {
        throw new DukeException("\t☹ OOPS!!! The description of a delete cannot be empty.");
      }
      
      int inputNumber;

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
      Task targetTask = list.remove(index);
      updateData(list);
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
    loadData(list);

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
