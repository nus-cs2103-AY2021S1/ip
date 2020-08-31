package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {

    private static final String dash = ("\u2500").repeat(5);
    private static final String greeting = "Hello! I'm Duke \n" +
            "What can I do for you?";
    public static final String task_list_number = "\nNow you have %d tasks in the list";
    public static final String task_removed = "Noted. I've removed this task: \n";
    private static final String task_read = "Here are the tasks in your list: \n";
    private static final String task_added = "Got it. I've added this task: \n";
    private static final String task_completed = "Nice! I've marked this task as complete. \n";
    private static final String ERROR = "Whoops! There was an error. \n";
    private static final String farewell = "Bye. Hope to see you again soon.";

    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printMessage("Hello from\n" + logo);
        printMessage(greeting);
    }

    public void displayTaskAdd(Task t, int numberOfItems) {
        printMessage(task_added + t.toString() + String.format(
                task_list_number, numberOfItems
        ));
    }

    public void displayTaskComplete(Task t) {
        printMessage(task_completed + t.toString());
    }

    public void displayTaskDelete(Task t, int numberOfItems) {
        printMessage(task_removed + t.toString() + String.format(
                task_list_number, numberOfItems
        ));
    }

    public void listTasks(TaskList tl) {
        printMessage(task_read + tl);
    }

    public void showError(String message) {
        printMessage(ERROR + message);
    }

    public void exit() {
        sc.close();
        printMessage(farewell);
    }

    private void printMessage(String message) {
        System.out.println(dash + "\n" + message + "\n" + dash);
    }

    public String readCommand() {
        String rawInput = sc.nextLine();
        return rawInput.trim();
    }


}
