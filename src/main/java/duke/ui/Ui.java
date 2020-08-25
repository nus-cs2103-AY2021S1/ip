package duke.ui;

import duke.tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Ui {

    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showWelcome() {
        format("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void showBye() {
        format("Bye. Hope to see you again soon!");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void format(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }

    public void formatLst(ArrayList<Task> lst) {
        showLine();
        int size = lst.size();
        if (size > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i <= lst.size(); i++) {
                System.out.println(i + "." + lst.get(i-1));
            }
        } else {
            System.out.println("There are no tasks in your list.");
        }
        showLine();
    }

    public void formatMarkAsDone(ArrayList<Task> lst, int num) {
        showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(lst.get(num));
        showLine();
    }

    public void formatAddTask(ArrayList<Task> lst, Task task) {
        int size = lst.size();
        showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d %s in the list.", size, size == 1 ? "task" : "tasks"));
        showLine();
    }

    public void formatDeleteTask(ArrayList<Task> lst, int num) {
        int sizeAfterDeletion = lst.size() - 1;
        showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(lst.get(num));
        System.out.println(String.format("Now you have %d %s in the list.", sizeAfterDeletion, sizeAfterDeletion == 1 ? "task" : "tasks"));
        showLine();
    }

    public void formatShowTasksOnDate(ArrayList<Task> tasksOnDate, LocalDate queryDate) {
        showLine();
        if (!tasksOnDate.isEmpty()) {
            System.out.println(String.format("The following deadlines/events are scheduled on %s.", queryDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))));
            for (int i = 1; i <= tasksOnDate.size(); i++) {
                System.out.println(i + "." + tasksOnDate.get(i-1));
            }
        } else {
            System.out.println(String.format("There are no deadlines/events scheduled on %s.", queryDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))));
        }
        showLine();
    }

    public void showError(Exception e) {
        showLine();
        if (e instanceof DateTimeParseException) {
            System.out.println("☹ OOPS!!! The format of the date given is invalid.");
        } else {
            System.out.println(e.getMessage());
        }
        showLine();
    }
}
