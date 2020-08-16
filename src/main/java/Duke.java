import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
  /** 1-indexed list */
  private List<Task> tasks;

  public Duke() {
    this.tasks = new ArrayList<>();
    tasks.add(null);
  }

  private int getNumberOfTasks() {
    return this.tasks.size() - 1;
  }

  public void greet() {
    System.out.println(String.format("Hello! I'm Duke\nWhat can I do for you?"));
  }

  public void add(String task) {
    this.tasks.add(new Task(task));
    System.out.println(String.format("added: %s", task));
  }

  public void list() {
    for (int i = 1; i < this.tasks.size(); i++) {
      System.out.println(String.format("%d. %s", i, this.tasks.get(i)));
    }
  }

  public void bye() {
    System.out.println("Bye! Hope to never see you again!");
  }

  public void done(String cmd) {
    String[] cmdParts = cmd.split(" ");
    try {
      this.done(Integer.valueOf(cmdParts[1]));
    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
      System.out.print("Input format is wrong. Please make sure it is `done <task-index>`");
    }
  }

  public void done(int index) {
    try {
      if (index == 0) {
        // Task list is 1-indexed, 0 is an out-of-bound value
        throw new IndexOutOfBoundsException();
      }

      Task task = this.tasks.get(index);
      task.setDone(true);
      System.out.println(String.format("Good job. This task is marked as done:\n %s", task));
    } catch (IndexOutOfBoundsException e) {
      System.out.println(String.format("Task at index %d doesn't exist", index));
      if (this.tasks.size() == 0) {
        System.out.println("There are no tasks currently.");
      } else {
        System.out.println(
            String.format(
                "There are %d tasks currently. Please a number between 1 and %d inclusive",
                this.getNumberOfTasks(), this.getNumberOfTasks()));
      }
    }
  }

  public static void main(String[] args) {
    Duke duke = new Duke();
    duke.greet();

    Scanner sc = new Scanner(System.in);
    String cmd = sc.nextLine();

    while (!cmd.equals("bye")) {
      if (cmd.equals("list")) {
        duke.list();
      } else if (cmd.length() >= 4 && cmd.substring(0, 4).equals("done")) {
        duke.done(cmd);
      } else {
        duke.add(cmd);
      }

      cmd = sc.nextLine();
    }

    duke.bye();
    sc.close();
  }
}
