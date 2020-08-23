package main.java.duke;

import main.java.duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Takes care of user interface for the chat bot.
 */
public class Ui {

    private Scanner sc;

    /**
     * Creates a new instance of Ui.
     * Initializes scanner.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when the program starts.
     */
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n"
                + "~ \n Hello I'm the Terminator \n What can I do for you? \n~");
    }

    /**
     * Displays the message when there is a loading error.
     */
    public void showLoadingError() {
        System.out.println(" ERROR DETECTED! UNABLE TO LOAD TASKLIST. \n CREATING NEW TASKLIST");
    }

    /**
     * Displays the list of task in the TaskList.
     *
     * @param taskList ArrayList of task.
     */
    public void showTaskList(ArrayList<Task> taskList) {
        System.out.println(" Here are targets in your kill list: ");
        if (!taskList.isEmpty()) {
            for (int i = 0; i < taskList.size(); i++) {
                int count = i + 1;
                System.out.println(String.format("   %d. ", count) + taskList.get(i).toString());
            }
        }
    }

    /**
     * Takes in user inputs.
     *
     * @return Input by the user.
     */
    public String readCommand() {
        String input = sc.nextLine().trim();
        return input;
    }

    /**
     * Displays text when the program stops.
     */
    public void showBye() {
        System.out.println(" I will be back! ");
    }

    /**
     * Displays error message.
     *
     * @param message Error message.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays divider.
     */
    public void showLine() {
        System.out.println("\n ******************************************************************** \n");
    }

    /**
     * Displays the list of matching task in the TaskList.
     *
     * @param tasks ArrayList of task.
     */
    public void showMatchingTask(ArrayList<Task> tasks) {
        System.out.println(" Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int count = i + 1;
            System.out.println(String.format("   %d. ", count) + tasks.get(i).toString());
        }
    }

    /**
     * Displays the task that is completed.
     *
     * @param task Task that is completed.
     */
    public void showDoneTask(Task task) {
        System.out.println(" Nice! Target Eliminated: \n   "
                + task.toString());
    }

    /**
     * Displays the task that has been undone.
     *
     * @param task Task that is undone.
     */
    public void showUndoTask(Task task) {
        System.out.println(" Task has been undone: \n   "
                + task.toString());
    }

    /**
     * Displays the task that is deleted.
     *
     * @param task Task that is deleted.
     * @param numTask Number of tasks.
     */
    public void showDeletedTask(Task task, int numTask) {
        System.out.println( String.format(" Noted. Target Scraped: \n   %s \n " +
                "Now you have %d tasks in the list. ", task.toString(), numTask));
    }

    /**
     * Displays the task that is added.
     *
     * @param task Task that is added.
     * @param numTask Number of tasks.
     */
    public void showTask(Task task, int numTask) {
        System.out.println(" Got it. I've added this task: ");
        System.out.println(String.format("   %s \n Now you have %d tasks in the list. ",
                task, numTask));
    }
}
