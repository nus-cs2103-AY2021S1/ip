/**
 * Encapsulates a User Interface
 * Deals with interactions with the user
 */

public class Ui {

    protected final String LINE = "____________________________________________________________";

    protected final String GREETING = "____________________________________________________________\n" +
            "Hello! I'm Duke\n" +
            "What can I do for you?\n" +
            "____________________________________________________________";

    /**
     * Prints out the greeting message
     */
    protected void greet() {
        System.out.println(GREETING);
    }

    /**
     * Prints out the farewell message
     */
    protected void farewell() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Prints out a line
     */
    protected void printLines() {
        System.out.println(LINE);
    }

    /**
     * Prints out the message when TaskList is shown
     */
    protected void provideListMsg() {
        System.out.println("Here are the tasks in your list:");
    }

    /**
     * Prints out the message when a Task is marked as done
     */
    protected void markAsDoneMsg(Task chosen) {
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(chosen);
        System.out.println(LINE);
    }

    /**
     * Prints out the message when a Task is deleted
     */
    protected void deleteMsg(int i, Task chosen) {
        System.out.println(LINE);
        System.out.println(" Noted. I've removed this task: ");
        System.out.println(chosen);
        System.out.println(" Now you have " + i + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Prints out the message when a Task is added to the TaskList
     */
    protected void addTaskToTasklistMsg(Task task, int i) {
        System.out.println(LINE);
        System.out.println(" Got it. I've added this task: ");
        System.out.println("  " + task);
        System.out.println(" Now you have " + i + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Prints out the message when results are found
     */
    protected void findMsg() {
        System.out.println(" Here are the matching tasks in your list:");
    }
}
