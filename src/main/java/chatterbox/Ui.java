package chatterbox;

import chatterbox.task.Task;

public class Ui {
    private static final String SEPARATOR = "++++++++++++++++++++++++++++++++++++++++++++++++++++++";

    private static String formatMessage(String msg) {
        return SEPARATOR + "\n" + msg + "\n" + SEPARATOR;
    }

    public static void showMessage(String msg) {
        System.out.println(formatMessage(msg));
    }

    public static void showAddTaskMessage(Task t, int totalTasks) {
        showMessage("Got it. I've added this task: \n"
                + t + "\n"
                + "Now you have " + totalTasks + " tasks in the list");
    }

    public static void showDeleteTaskMessage(Task t, int totalTasks) {
        showMessage("Noted! I've removed this task from your list: \n"
                + t + "\n"
                + " Now you have " + totalTasks + " tasks in the list.");
    }

    public static void showWelcomeMessage() {
        System.out.println("Hello I'm Chatterbox. What can I do for you?");
    }

    public static void showErrorMessage(String error) {
        showMessage("☹ OOPS!!! " + error);
    }

    public static void showFarewellMessage() {
        showMessage("Goodbye! Hope to see you again soon!");
    }
}
