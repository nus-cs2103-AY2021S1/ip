import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ui prints outputs to interact with users.
 */
public class Ui {
    private Scanner sc;

    /**
     * Print statement when a task is added into TaskList.
     *
     * @param task
     */
    public String printAddedTask(Task task, int numTask) {
        return "Got it. I've added this task:\n" + task + "\n"
                + "Now you have " + String.valueOf(numTask) + " tasks in the list.";
    }

    public String markAsDone(Task task) {
        return "Nice! I've marked this task as done:\n [✓] " + task.description;
    }

    /**
     * Delete a task using its position from TaskList.
     *
     * @param task Task
     * @param numTaskLeft int
     */
    public String deleteTask(Task task, int numTaskLeft) {
        return "Noted. I've removed this task: \n" + task + "\n"
                + "Now you have " + Integer.valueOf(numTaskLeft) + " tasks in the list.";
    }

    /**
     * Print all the tasks in the TaskList.
     *
     * @param tasks
     */
    public String printAllTask(TaskList tasks) {
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
     */
    public String printSearchedTask(ArrayList<Task> tasks) {
        int numTask = 0;
        String output = "Here are the matching tasks in your list:\n";
        while (numTask < tasks.size()) {
            output = output + Integer.valueOf(numTask + 1) + "." + tasks.get(numTask) + "\n";
            numTask++;
        }
        return output;
    }

    /**
     * Ui constructor to initialise scanner.
     */
    public Ui() {
        sc = new Scanner(System.in);
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
     * Print the error.
     *
     * @param error String
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Show a horizontal dashes.
     */
    public void showLine() {
        String line = "--------------------------------------";
        System.out.println(line);
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
     * Stop Duke and close scanner.
     */
    public String closeDuke() {
        String bye = "Bye. Hope to see you again soon!";
        sc.close();
        return bye;
    }
}