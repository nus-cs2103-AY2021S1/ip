package duke.ui;

import java.util.Scanner;

import duke.task.Task;


/**
 * Represents a class that deals with interactions with the user
 */
public class Ui {

    /**
     * Prints the welcome message for the Duke application
     */
    public void showWelcome() {
        // print welcome message
        System.out.println("    _________________________________\n"
                + "    Hello! I'm Duke\n"
                + "    What can I do for you?\n"
                + "    _________________________________");
    }

    /**
     * Prints a line divider
     */
    public void showLine() {
        System.out.println("    _________________________________");
    }

    /**
     * Returns the user command entered into the command line as a string
     * @return user command
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Prints the error message of the exception caught
     * @param message error message of the exception caught
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Returns the template response for adding new task as a string.
     * @param task
     * @param sizeOfTaskList
     * @return String of the template response after adding a new task.
     */
    public String showAddTaskResponse(Task task, int sizeOfTaskList) {
        return "    Got it. I've added this task:\n"
                + "        " + task + '\n'
                + "    Now you have " + sizeOfTaskList + " tasks in the list.";
    }

    public String showIsDuplicateMessage() {
        return "    This task is already in your task list!";
    }

}
