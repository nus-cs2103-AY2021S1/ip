package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    /** Scanner to handle user input. */
    private Scanner sc;

    /**
     * Initialises the scanner the take in user input.
     */
    Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads user inputs, a line at a time.
     *
     * @return String that is read from user input.
     */
    public String readCommand() {
        String userInput = this.sc.nextLine();
        return userInput;
    }


    /**
     * Prints all of the matching tasks that are stored in the ArrayList.
     *
     * @param foundTasks The ArrayList containing all of the matching tasks.
     */
    public void printMatches(ArrayList<Task> foundTasks) {
        showLine();
        System.out.println("\t Here are the matching tasks in your list: ");
        for (Task t: foundTasks) {
            System.out.println("\t " + t.toString());
        }
        showLine();
    }

    private void showLine() {
        System.out.println("\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0");
    }

    private void showTotalTasks(int total) {
        System.out.println("\t Now you have " + total + " tasks in the task list.");
    }

    /**
     * Prints the default message after every successful addition of a task.
     *
     * @param taskList Task list that stores all of the tasks.
     */
    public void showTaskAdded(TaskList taskList) {
        showLine();
        System.out.println("\t Got it. I've added this task: " + "\n\t  "
                + taskList.getTask(taskList.totalTask() - 1).toString());
        showTotalTasks(taskList.totalTask());
        showLine();
    }

    /**
     * Prints the default done message after every successful change in task done status.
     *
     * @param task Task that is marked done.
     */
    public void showDone(Task task) {
        showLine();
        System.out.println("\t Nice! I've marked this task as done: " + "\n\t   " + task.toString());
        showLine();
    }

    /**
     * Prints the default delete message after every successful deletion of a task.
     *
     * @param taskList Task list that stores all of the tasks.
     * @param task Task that is deleted.
     */
    public void showDelete(TaskList taskList, Task task) {
        showLine();
        System.out.println("\t Noted. I've removed this task: " + "\n\t   " + task.toString());
        showTotalTasks(taskList.totalTask());
        showLine();
    }

    /**
     * Prints out all the tasks stored in the specified task list.
     *
     * @param taskList Task list that contains the tasks to be printed.
     */
    public void printList(TaskList taskList) {
        showLine();
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < taskList.totalTask(); i++) {
            System.out.println("\t " + (i + 1) + "." + taskList.getTask(i).toString());
        }
        showLine();
    }

    /**
     * Displays any error message that is the result of an exception.
     *
     * @param errorMessage Error message that is displayed to the user.
     */
    public void showError(String errorMessage) {
        showLine();
        System.out.println("\t " + errorMessage);
        showLine();
    }
    /**
     * Displays any error message that is the result of a file loading exception.
     */
    public void showLoadingError() {
        showLine();
        System.out.println("\tSomething wrong with loading from your file.. Proceeding to create new empty TaskList");
        showLine();
    }

    /**
     * Displays the welcome message of the Duke chat-bot.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ \uD83D\uDD34 \uD83D\uDD34 \\\n"
                + "| |_| | |_| |   <  \\__/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello! I'm\n" + logo);
        System.out.println("\nWhat can I do for you?");
    }
}
