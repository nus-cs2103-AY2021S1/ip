import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ui prints outputs to interact with users.
 */
public class Ui {
    private Scanner sc;
    /**
     * Ui constructor to initialise scanner.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }
    /**
     * Print statement when a task is added into TaskList.
     *
     * @param task
     * @return String
     */
    public String printAddedTask(Task task, int numTask) {
        return "Got it. I've added this task:\n" + task + "\n"
                + "Now you have " + String.valueOf(numTask) + " tasks in the list.";
    }

    /**
     * Print the task that is mark as done.
     *
     * @param task
     * @return String
     */
    public String markAsDone(Task task) {
        return "Nice! I've marked this task as done:\n" + "\u2713" + task.description;
    }

    /**
     * Print the task that is being deleted.
     *
     * @param task Task
     * @param numTaskLeft int
     * @return String
     */
    public String deleteTask(Task task, int numTaskLeft) {
        return "Noted. I've removed this task: \n" + task + "\n"
                + "Now you have " + Integer.valueOf(numTaskLeft) + " tasks in the list.";
    }

    /**
     * Print all the tasks in the TaskList.
     *
     * @param tasks
     * @return String
     * @throws IndexExceedException
     */
    public String printAllTask(TaskList tasks) throws IndexExceedException {
        int numTask = 0;
        String output = "Here are the tasks in your list:\n";
        while (numTask < tasks.size()) {
            output = output + Integer.valueOf(numTask + 1) + "." + tasks.getTask(numTask) + "\n";
            numTask++;
        }
        return output;
    }

    /**
     * Print all the matching tasks.
     *
     * @param tasks ArrayList
     * @return String
     */
    public String printSearchedTask(ArrayList<Task> tasks) {
        int numTask = 0;
        String output = "Here are the matching tasks in your list:\n";
        if (tasks.isEmpty()) {
            output = "There are no matching task in your list. :(";
        } else {
            while (numTask < tasks.size()) {
                output = output + Integer.valueOf(numTask + 1) + "." + tasks.get(numTask) + "\n";
                numTask++;
            }
        }
        return output;
    }

    /**
     * Read the input from user and return it as a string.
     *
     * @return String
     */
    public String readCommand() {
        String temp = null;
        if (sc.hasNextLine()) {
            temp = sc.nextLine();
        }
        return temp;
    }

    /**
     * Print the error in console and return error in string.
     *
     * @param error String
     * @return String
     */
    public String showError(String error) {
        System.out.println(error);
        return error;
    }

    /**
     * Show a horizontal dashes.
     *
     * @return String of line
     */
    public String showLine() {
        String line = "---------------------------------------------------";
        return line;
    }

    /**
     * Show loading error when loading storage.
     */
    public void showLoadingError() {
        String error = "error loading Duke";
        System.out.println(error);
    }

    /**
     * Print welcome when the Duke is started.
     */
    public void showWelcome() {
        String greeting = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greeting);
    }

    /**
     * Return string bye and stop duke.
     *
     * @return String
     */
    public String closeDuke() {
        String bye = "Bye. Hope to see you again soon!";
        sc.close();
        return bye;
    }
}
