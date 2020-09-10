package ui;

import task.Task;

public class Ui {

    /**
     * Prints welcome message.
     */
    public static String showWelcome() {
        return "Hello! I'm Duke\n" +
                "What can I do for you?";
    }

    /**
     * Prints goodbye message
     */
    public static String goodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints header and task added.
     *
     * @param task Task to print
     */
    public static String showAddTask(Task task) {
        return "Go it. I've added this task:\n" + task.toString();
    }

    /**
     * Prints header and task done.
     *
     * @param task Task to print
     */
    public static String showDoneTask(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Prints header and task removed.
     *
     * @param task Task to print
     */
    public static String showRemovedTask(Task task) {
        return "Noted. I've removed this task:\n" + task.toString();
    }

    /**
     * Prints header and number of tasks in list.
     *
     * @param size
     */
    public static String showTotalTasks(int size) {
        String plural = size != 1 ? "tasks" : "task";
        return "Now you have " + size + " " + plural + " in the list.";
    }


    public static String showFindTask(String result) {
        return "Here are the matching tasks in your list:\n" + result;
    }

    /**
     * Prints error message
     *
     * @param e Exception to print
     */
    public static String showError(Exception e) {
        return e.getMessage();
    }

    public  static String showTaggedTask(Task task){
        return "Noted. The following task has been tagged!" + "\n" + task.toString() + "\n";
    }
}