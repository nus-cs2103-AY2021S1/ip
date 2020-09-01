package duke;

import java.util.Scanner;

import duke.task.Task;

public class Ui {

    public static final String TASK_LIST_NUMBER = "\nNow you have %d tasks in the list";
    public static final String TASK_REMOVED = "Noted. I've removed this task: \n";
    private static final String TASK_READ = "Here are the tasks in your list: \n";
    private static final String TASK_ADDED = "Got it. I've added this task: \n";
    private static final String TASK_COMPLETED = "Nice! I've marked this task as complete. \n";
    private static final String ERROR = "Whoops! There was an error. \n";
    private static final String FAREWELL = "Bye. Hope to see you again soon.";
    private static final String DASH = ("\u2500").repeat(5);
    private static final String GREETING = "Hello! I'm Duke \n"
            + "What can I do for you?";

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
        printMessage(GREETING);
    }

    public void displayTaskAdd(Task t, int numberOfItems) {
        printMessage(TASK_ADDED + t.toString() + String.format(
                TASK_LIST_NUMBER, numberOfItems
        ));
    }

    public void displayTaskComplete(Task t) {
        printMessage(TASK_COMPLETED + t.toString());
    }

    public void displayTaskDelete(Task t, int numberOfItems) {
        printMessage(TASK_REMOVED + t.toString() + String.format(
                TASK_LIST_NUMBER, numberOfItems
        ));
    }

    public void listTasks(TaskList tl) {
        printMessage(TASK_READ + tl);
    }

    public void showError(String message) {
        printMessage(ERROR + message);
    }

    public void exit() {
        sc.close();
        printMessage(FAREWELL);
    }

    private void printMessage(String message) {
        System.out.println(DASH + "\n" + message + "\n" + DASH);
    }

    public String readCommand() {
        String rawInput = sc.nextLine();
        return rawInput.trim();
    }


}
