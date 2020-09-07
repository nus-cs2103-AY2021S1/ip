package Duke;

import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    /**
     * Constructor initialises Scanner.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints welcome message
     */
    public String showWelcome() {
        return "Hello! I'm Duke\n" +
                "What can I do for you?";
    }

    /**
     * Reads and returns system input.
     *
     * @return System input.
     */
    public String readLine() {
        return sc.nextLine();
    }

    /**
     * Closes Scanner
     */
    public void close() {
        this.sc.close();
    }

    /**
     * Prints goodbye message
     */
    public String goodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints header and task added.
     *
     * @param task Task to print
     */
    public String showAddTask(Task task) {
        return "Go it. I've added this task:\n" + task.toString();
    }

    /**
     * Prints header and task done.
     *
     * @param task Task to print
     */
    public String showDoneTask(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Prints header and task removed.
     *
     * @param task Task to print
     */
    public String showRemovedTask(Task task) {
        return "Noted. I've removed this task:\n" + task.toString();
    }

    /**
     * Prints header and number of tasks in list.
     *
     * @param size
     */
    public String showTotalTasks(int size) {
        String plural = size != 1 ? "tasks" : "task";
        return "Now you have " + size + " " + plural + " in the list.";
    }


    public String showFindTask(String result) {
        return "Here are the matching tasks in your list:\n" + result;
    }

    public void printMessage(String output) {
        System.out.println(output);
    }

    /**
     * Prints error message
     *
     * @param e Exception to print
     */
    public String showError(Exception e) {
        return e.getMessage();
    }

}