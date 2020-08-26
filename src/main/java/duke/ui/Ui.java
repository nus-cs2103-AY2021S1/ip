package duke.ui;

import duke.tasklist.TaskList;
import duke.tasks.Task;

import java.time.LocalDate;

import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);

    private static String instructions = "Instructions on using Duke:\n" +
            "list : key in list to show your list of tasks. key in a date after list to show your list of tasks on that date\n" +
            "date and time : follow yyyy-mm-dd format when keying in dates and hh:mm format when keying in times\n";

    public String readCommand() {
        return sc.nextLine();
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n");
        System.out.println(instructions);
        System.out.println("What can I do for you?");
    }

    public void displayList(TaskList tasklist, LocalDate date) {
        int i = 1;
        if (date == null) {
            for (Task t : tasklist.getList()) {
                System.out.println(i + "." + t);
                i++;
            }
        } else {
            for (Task t : tasklist.getList()) {
                if (t.getDate() != null && t.getDate().equals(date)) {
                    System.out.println(i + "." + t);
                    i++;
                }
            }
        }
    }

    public void printDone(Task task) {
        System.out.println("Nice! I've marked this task as done:\n\t" + task);
    }

    public void printDelete(Task task) {
        System.out.println("Noted. I've removed this task:\n\t" + task);
    }

    public void printAdd(Task task, int size) {
        System.out.println("Got it. I've added this task:\n\t" + task);
        System.out.println("Now you have " + size + " tasks in the list");
    }

    public void showLoadingError() {
        System.out.println("Error loading disk");
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void printGoodbye() {
        System.out.println("Goodbye! See you again :-)");
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }
}
