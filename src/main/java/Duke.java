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
        ToDo newToDo = new ToDo(command[1]);
        list.add(newToDo);

        System.out.println("\t" + divider);
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t\t" + newToDo);
        System.out.println("\tNow you have " + list.size() + " tasks in the list.");
        System.out.println("\t" + divider);
      } else {
        list.add(new Task(input));

        System.out.println("\t" + divider);
        System.out.println("\tadded: " + input);
        System.out.println("\t" + divider + "\n");
      }
    }

    System.out.println("\t" + divider);
    System.out.println("\tBye. Hope to see you again soon!");
    System.out.println("\t" + divider);
  }
}
