package duke.ui;

import duke.exceptions.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

/**
 * Represents the output shown to the user.
 */
public class Ui {

    private static final String DIVIDER = "===================================================";

    private final Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Formats the welcome message shown to the user.
     */
    public void printWelcomeMessage() {
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";

        String welcome = logo + "\n"
                + DIVIDER + "\n"
                + "Hello! I'm Ray\n" + "Machine learning and AI powers me\n"
                + DIVIDER + "\n";
        System.out.println(welcome);
    }

    /**
     * Prompts the user to enter a command in the ui and returns the input as a String to be
     * parsed.
     *
     * @return the string the user input
     */
    public String readCommand() {
        System.out.println(DIVIDER);
        System.out.println("Please enter a command");
        return in.nextLine().trim();

    }

    public void printDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Prints a formatted message that includes the task the user added and
     * the number of tasks in the list after the use adds a task.
     *
     * @param taskList the ArrayList that stores all of the user's tasks
     * @param task the task the user chooses to add
     */
    public void printAddTaskMessage(TaskList taskList, Task task) {
        String message = "Got it. I've added this task:\n " + task + "\nNow you have "
                + taskList.size() + " in the list";
        System.out.println(message);
    }

    /**
     * Prints a formatted message that includes the task the user specified to mark as done.
     *
     * @param task the task the user chooses to mark as done
     */
    public void printDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:\n " + task);
    }

    /**
     * Prints a formatted message that includes the task the user chooses to delete and the
     * size of the task list after the user deletes the task.
     *
     * @param taskList the ArrayList that stores all tasks of the user
     * @param task the task the user chooses to delete
     */
    public void printDeleteMessage(TaskList taskList, Task task) {
        System.out.println("Noted. I've removed this duke.task:\n " + task
                + "\nNow you have " + taskList.size() + " in the list.");
    }

    /**
     * Prints a formatted message that lists all tasks the user currently has
     *
     * @param taskList the ArrayList that stores all tasks of the user.
     */
    public void printAllTasks(TaskList taskList) {
        System.out.println("Here are the tasks in your list");
        listAllTasks(taskList);
    }

    /**
     * Prints a formatted message that list all tasks the user saved from his/her previous session.
     *
     * @param taskList the ArrayList that stores all tasks from the user's previous session.
     */
    public void printDatabaseTasks(TaskList taskList) {
        if (taskList.size() == 0) {
            System.out.println("You have no tasks currently");
        } else {
            System.out.println("Here are the saved from your last session");
            listAllTasks(taskList);
        }
    }

    public void printFindMessage() {
        System.out.println("Here are the matching tasks in your list:");
    }

    private void listAllTasks(TaskList taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            try {
                String output = (i + 1) + "." + taskList.getTask(i + 1);
                System.out.println(output);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void displayErrorMessage(String message) {
        System.out.println(message + ":((((");
    }


}
