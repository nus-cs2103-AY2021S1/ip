package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    /** Scanner to handle user input. */
    private final Scanner sc;

    /**
     * Initialises the scanner the take in user input.
     */
    Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Reads user inputs, a line at a time.
     *
     * @return String that is read from user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }


    /**
     * Prints all of the matching tasks that are stored in the ArrayList.
     *
     * @param foundTasks The ArrayList containing all of the matching tasks.
     * @return String to be printed out.
     */
    public String printMatches(ArrayList<Task> foundTasks) {
        if (foundTasks.size() < 1) {
            return " Sorry, no tasks were found!";
        }
        String returnString = " Here are the matching tasks in your list: ";
        for (Task t: foundTasks) {
            returnString += "\n\t " + t.toString();
        }
        return returnString;
    }


    private String showTotalTasks(int total) {
        return "\n Now you have " + total + " tasks in the task list.";
    }

    /**
     * Prints the default message after every successful addition of a task.
     *
     * @param taskList Task list that stores all of the tasks.
     * @return String to be printed out.
     */
    public String showTaskAdded(TaskList taskList) {
        return " Got it. I've added this task: " + "\n\t"
                + taskList.getTask(taskList.totalTask() - 1).toString()
                + showTotalTasks(taskList.totalTask());
    }

    /**
     * Prints the default done message after every successful change in task done status.
     *
     * @param task Task that is marked done.
     * @return String to be printed out.
     */
    public String showDone(Task task) {
        return " Nice! I've marked this task as done: "
                + "\n\t"
                + task.toString();
    }

    /**
     * Prints the default delete message after every successful deletion of a task.
     *
     * @param taskList Task list that stores all of the tasks.
     * @param task Task that is deleted.
     * @return String to be printed out.
     */
    public String showDelete(TaskList taskList, Task task) {
        return " Noted. I've removed this task: "
                + "\n\t"
                + task.toString()
                + showTotalTasks(taskList.totalTask());
    }

    /**
     * Prints out all the tasks stored in the specified task list.
     *
     * @param taskList Task list that contains the tasks to be printed.
     * @return String to be printed out.
     */
    public String printList(TaskList taskList) {
        String returnString = " Here are the tasks in your list: ";
        for (int i = 0; i < taskList.totalTask(); i++) {
            returnString += "\n " + (i + 1) + ". " + taskList.getTask(i).toString();
        }
        return returnString;
    }

    /**
     * Displays any error message that is the result of an exception.
     *
     * @param errorMessage Error message that is displayed to the user.
     * @return String to be printed out.
     */
    public String showError(String errorMessage) {
        return "\t "
                + errorMessage;
    }
    /**
     * Displays any error message that is the result of a file loading exception.
     *
     * @return String to be printed out.
     */
    public String showLoadingError() {
        return " Something wrong with loading from your file.. Proceeding to create new empty TaskList: ";
    }

    /**
     * Displays the welcome message of the Duke chat-bot.
     *
     * @return String to be printed out to welcome the user.
     */
    public String showWelcome() {
        String logo = "___          __        \n"
                    + "| __\\___ __| | _____ \n"
                    + "| | | | | | | |/ \uD83D\uDD34 \uD83D\uDD34 \\\n"
                    + "| |_| | |_| |   <  \\__/\n"
                    + "|____/\\__,|_|\\_\\___|\n";

        return "Hello! I'm\n"
                + logo + "\nWhat can I do for you?";
    }
}
