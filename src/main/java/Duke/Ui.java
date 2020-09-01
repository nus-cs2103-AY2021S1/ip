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
    public void showWelcome() {
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
    }

    /**
     * Reads and returns system input.
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
    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints header and task added.
     * @param task Task to print
     */
    public void showAddTask(Task task) {
        System.out.println("Go it. I've added this task:\n" + task.toString());
    }

    /**
     * Prints header and task done.
     * @param task Task to print
     */
    public void showDoneTask(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Prints header and task removed.
     * @param task Task to print
     */
    public void showRemovedTask(Task task) {
        System.out.println("Noted. I've removed this task:\n" + task.toString());
    }

    /**
     * Prints header and number of tasks in list.
     * @param size
     */
    public void showTotalTasks(int size) {
        String plural = size != 1 ? "tasks" : "task";
        System.out.println("Now you have " + size + " " + plural + " in the list.");
    }

    public void printMessage(String output) {
        System.out.println(output);
    }

    /**
     * Prints error message
     * @param e Exception to print
     */
    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }

}