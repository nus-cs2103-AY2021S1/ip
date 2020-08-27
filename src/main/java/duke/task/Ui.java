package duke.task;

import java.util.Scanner;

/**
 * Encapsulates an Ui object that interacts with the user.
 */
public class Ui {
    Scanner sc;

    /**
     * Initialises an Ui object.
     */
    protected Ui() {
        sc = new Scanner(System.in);
    }

    protected String readCommand() {
        return sc.nextLine();
    }

    protected void showWelcome() {
        System.out.println("Hello! I'm Yoo ( ^-^)/ \nWhat can I do for you?");
    }

    protected void showError(String message) {
        System.out.println(message);
    }

    protected void showExit() {
        sc.close();
        System.out.println("Bye! Come back soon ( ^-^)/");
    }

    /**
     * Prints the list of tasks.
     * @param tasks List of tasks.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println(tasks);
    }

    public void congratulate() {
        System.out.println("Good job completing the task! \u256D( \uFF65\u3142\uFF65)\u0648");
    }

    public void confirmDelete(Task t, TaskList tasks) {
        System.out.println("I've deleted the following task! \n" + t);
        System.out.println("Now you have " + tasks.length()
                + " tasks in the list (\u00B4\u30FB\u03C9\u30FB\uFF40)");
    }

    public void confirmAdd(Task t, TaskList tasks) {
        System.out.println("I've added the following task! \n" + t);
        System.out.println("Now you have " + tasks.length()
                + " tasks in the list (\u00B4\u30FB\u03C9\u30FB\uFF40)");
    }
}
