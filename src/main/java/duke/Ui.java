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
    private static final String TASK_PRIORITY_ADDED = "Got it. I've added a priority to this task: \n";

    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Displays a basic greeting.
     */
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printMessage("Hello from\n" + logo);
        printMessage(GREETING);
    }

    public String displayTaskAdd(Task t, int numberOfItems) {
        return printMessage(TASK_ADDED + t.toString() + String.format(
                TASK_LIST_NUMBER, numberOfItems
        ));
    }

    public String displayTaskComplete(Task t) {
        return printMessage(TASK_COMPLETED + t.toString());
    }

    public String displayTaskDelete(Task t, int numberOfItems) {
        return printMessage(TASK_REMOVED + t.toString() + String.format(
                TASK_LIST_NUMBER, numberOfItems
        ));
    }

    public String listTasks(TaskList tl) {
        return printMessage(TASK_READ + tl);
    }

    public String showError(String message) {
        return printMessage(ERROR + message);
    }

    public String exit() {
        sc.close();
        return printMessage(FAREWELL);
    }

    private String printMessage(String message) {
        String prettyMessage = DASH + "\n" + message + "\n" + DASH;
        System.out.println(prettyMessage);
        return prettyMessage;
    }

    public String readCommand() {
        String rawInput = sc.nextLine();
        return rawInput.trim();
    }

    public String displayTaskAddPriority(Task task) {
        return printMessage(TASK_PRIORITY_ADDED + task.toString());
    }
}
