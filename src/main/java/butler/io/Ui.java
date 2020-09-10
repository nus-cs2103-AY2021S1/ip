package butler.io;

import java.util.Scanner;

import butler.task.Task;
import butler.task.TaskList;

/**
 * Represents a user interface that interacts with the user.
 * <code>Ui</code> prints statements for the user and scans user input.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructs a user interface.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints out a welcome message.
     */
    public void showWelcome() {
        String greetings = "Welcome! I am your personal manager, Butler.\n"
                + "How may I help you today?\n";
        System.out.println(greetings);
    }

    /**
     * Reads the user input.
     * Only one line of user input is read.
     *
     * @return User input
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints a dotted line separator.
     */
    public void showLine() {
        System.out.println("\n------------------------------\n");
    }

    /**
     * Prints the <code>errorMessage</code> given.
     *
     * @param errorMessage Error message to be printed.
     */
    public void showError(String errorMessage) {
        System.out.println("ERRORS: " + errorMessage);
    }

    /**
     * Prints the farewell message.
     */
    public void showExit() {
        String farewells = "It is my honor to have served you.\n"
                + "Please come back again.";
        System.out.println(farewells);
    }

    /**
     * Prints the loading error message.
     */
    public void showLoadingError() {
        String loadingErrorMessage = "tasks.txt file was not detected.\n"
                + "A new task list will be created.";
        System.out.println(loadingErrorMessage);
    }

    /**
     * Prints a response message that a task is marked as completed.
     * The task is specified by the <code>taskIndex</code> given.
     *
     * @param taskIndex Index of task marked as completed.
     */
    public void showTaskCompleted(int taskIndex) {
        String msg = "Task " + taskIndex + " has been marked as complete.";
        System.out.println(msg);
    }

    /**
     * Prints a response message that a task is added.
     * Task that was added is specified by <code>task</code>.
     *
     * @param task Task that was added.
     */
    public void showTaskAdded(Task task) {
        String msg = "The following task has been added to task list.\n" + task;
        System.out.println(msg);
    }

    /**
     * Prints out the list of tasks in the given <code>taskList</code>.
     *
     * @param taskList List of tasks to be printed out.
     */
    public void printTaskList(TaskList taskList) {
        String msg = "Here are your list of tasks.\n"
                + "You have " + taskList.getSize()
                + (taskList.getSize() > 1 ? " tasks" : " task")
                + " in total.\n";
        for (int i = 0; i < taskList.getSize(); i++) {
            msg += "\n" + (i + 1) + ": " + taskList.getTask(i);
        }
        System.out.println(msg);
    }

    /**
     * Prints out a response message that a task was deleted.
     * Deleted task is specified by <code>taskIndex</code>.
     *
     * @param taskIndex Index of task that was deleted.
     */
    public void showTaskDeleted(int taskIndex) {
        String msg = "Task " + taskIndex + " has been deleted.";
        System.out.println(msg);
    }

    /**
     * Prints out the given filtered list of tasks.
     *
     * @param taskList List of tasks to be printed out.
     */
    public void printFilteredTaskList(TaskList taskList) {
        String msg = "Here are the matching tasks in your list.\n"
                + "You have " + taskList.getSize()
                + (taskList.getSize() > 1 ? " tasks" : " task")
                + " in total.\n";
        for (int i = 0; i < taskList.getSize(); i++) {
            msg += "\n" + (i + 1) + ": " + taskList.getTask(i);
        }
        System.out.println(msg);
    }
}
