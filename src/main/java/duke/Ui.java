package duke;

import duke.task.Task;
import java.util.Scanner;

/**
 * Represents the part of Duke that deals with user interaction.
 */
public class Ui {
    private final Scanner sc;

    /**
     * Creates a new Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads user input.
     *
     * @return the user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints Duke's response to the user interaction with dividers(to improve readability).
     *
     * @param output Duke's response.
     */
    public void printLines(String output) {
        System.out.println("______________________________________________________________________");
        System.out.println(output);
        System.out.println("______________________________________________________________________");
    }

    /**
     * Shows a welcome message to the user.
     */
    public void showWelcome() {
        printLines("Hello! I'm Duke\n" + "What can I do for you?");
    }

    /**
     * Shows a farewell message to the user.
     */
    public void showExit() {
        printLines("Bye. I hope to see you again soon!");
    }

    /**
     * Shows the entire list of tasks.
     * If there are no tasks in the list, Duke will inform the user instead of showing an empty list.
     *
     * @param tasks the list of tasks.
     */
    public void showList(TaskList tasks) {
        if (tasks.getNumOfTasks() == 0) {
            printLines("You currently have no tasks in your list.");
        } else {
            printLines("Here are the tasks in your list:\n" + tasks);
        }
    }

    /**
     * Shows a message to the user that a task has been successfully added into the task list.
     * Duke will then recalculate the total number of tasks and inform the user.
     *
     * @param task the task that has been added into the task list.
     * @param tasks the task list.
     */
    public void showAdded(Task task, TaskList tasks) {
        printLines("Task added successfully!\n\t" + task + showNumOfTasks(tasks));
    }

    /**
     * Shows a message to the user that a task has been successfully marked as done.
     *
     * @param task the task that has been marked as done.
     */
    public void showDone(Task task) {
        printLines("Task completed successfully!\n\t" + task);
    }

    /**
     * Shows a message to the user that a task has been successfully deleted from the task list.
     *
     * @param task the task that has been deleted from the task list.
     * @param tasks the task list.
     */
    public void showDeleted(Task task, TaskList tasks) {
        printLines("Task deleted successfully!\n\t" + task + showNumOfTasks(tasks));
    }

    /**
     * Shows the user how many tasks are in the task list.
     *
     * @param tasks the task list.
     * @return a message that informs the user of the number of tasks in the list.
     */
    public String showNumOfTasks(TaskList tasks) {
        return String.format("\nNow you have %d task(s) in the list.", tasks.getNumOfTasks());
    }

    /**
     * Shows an error message as and when an exception is thrown.
     *
     * @param e the exception thrown.
     */
    public void showErrorMessage(Exception e) {
        printLines(e.getMessage());
    }
}