package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

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
     *
     * @return String containing greeting message.
     */
    public static String greet() {
        return ("~ \n Hello I'm the Terminator \n What can I do for you? \n~");
    }

    /**
     * Displays the message when there is a loading error.
     *
     * @return String containing loading error message.
     */
    public String showLoadingError() {
        return " ERROR DETECTED! UNABLE TO LOAD TASKLIST. \n CREATING NEW TASKLIST";
    }

    /**
     * Displays the list of task in the TaskList.
     *
     * @param taskList ArrayList of task.
     * @return String containing all the tasks.
     */
    public String showTaskList(ArrayList<Task> taskList) {
        StringBuilder output = new StringBuilder(" Here are targets in your kill list: \n");
        if (!taskList.isEmpty()) {
            for (int i = 0; i < taskList.size(); i++) {
                int count = i + 1;
                output.append(String.format("   %d. ", count)).append(taskList.get(i).toString());
            }
        }
        return output.toString();
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
     *
     * @return String containing message to terminate.
     */
    public String showBye() {
        return " I will be back! ";
    }

    /**
     * Displays error message.
     *
     * @param message Error message.
     * @return String containing error message.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Displays divider.
     *
     * @return String containing a divider.
     */
    public String showLine() {
        return "\n ******************************************************************** \n";
    }

    /**
     * Displays the list of matching task in the TaskList.
     *
     * @param tasks ArrayList of task.
     * @return String that contains list of matching tasks.
     */
    public String showMatchingTask(ArrayList<Task> tasks) {
        StringBuilder output = new StringBuilder(" Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            int count = i + 1;
            output.append(String.format("   %d. ", count)).append(tasks.get(i).toString());
        }
        return output.toString();
    }

    /**
     * Displays the task that is completed.
     *
     * @param task Task that is completed.
     * @return String that contains the task that is done.
     */
    public String showDoneTask(Task task) {
        return " Nice! Target Eliminated: \n   "
                + task.toString();
    }

    /**
     * Displays the task that has been undone.
     *
     * @param task Task that is undone.
     * @return String that contains the task that has been undone.
     */
    public String showUndoTask(Task task) {
        return " Task has been undone: \n   "
                + task.toString();
    }

    /**
     * Displays the task that is deleted.
     *
     * @param task Task that is deleted.
     * @param numTask Number of tasks.
     * @return String that contains the deleted task.
     */
    public String showDeletedTask(Task task, int numTask) {
        return String.format(" Noted. Target Scraped: \n   %s \n "
                + "Now you have %d tasks in the list. ", task.toString(), numTask);
    }

    /**
     * Displays the task that is added.
     *
     * @param task Task that is added.
     * @param numTask Number of tasks.
     * @return String containing the task added.
     */
    public String showTask(Task task, int numTask) {
        String output = new StringBuilder().append(" Got it. I've added this task: \n")
                .append(
                        String.format("   %s \n Now you have %d tasks in the list. ", task, numTask)).toString();
        return output;
    }
}
