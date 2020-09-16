package duke;
import java.util.List;
import java.util.Scanner;

/**
 * Ui class prints various response by Duke object, handles user interactions.
 */

public class Ui {
    private Scanner sc = new Scanner(System.in);

    public Ui() {
    }

    /**
     * Method to return command error message.
     * @return error message.
     */
    public static String commandError() {
        return "Command not found";
    }

    public static String showError(DukeException e) {
        return e.toString();
    }

    public static String welcome() {
        return "Hello! I'm meimei ^_^\nWhat can I do for you?";
    }

    /**
     * Prints the tasks stated.
     * @param tasks to be printed.
     * @return tasks in string format.
     */
    public static String printTaskList(List<Task> tasks) {
        String result = "";
        result += "Here are the tasks in your list: \n";
        for (int i = 0; i < tasks.size(); i++) {
            result += (i + 1) + "." + tasks.get(i).toString() + "\n";
        }
        return result;
    }

    /**
     * Method to return the list of task containing keyword.
     * @param taskList
     * @return list of task in string format.
     */
    public static String tasksFound(List<Task> taskList) {
        if (taskList.isEmpty()) {
            return "No matching task found";
        } else {
            String result = "Meimei found these matching tasks:\n";
            for (int i = 0; i < taskList.size(); i++) {
                result += (i + 1) + "." + taskList.get(i).toString() + "\n";
            }
            return result;
        }
    }

    /**
     * Method to print exception messages.
     * @param exception type of duke exception.
     * @return exception message.
     */
    public static String dukeException(DukeException exception) {
        return exception.toString();
    }

    /**
     * duke asks for new user input.
     * @return the user input.
     */
    public String ask() {
        return sc.nextLine();
    }

    public String showLoadingError() {
        return "File not found >w<";
    }

    public static String bye() {
        return "Bye! Meimei will miss u :(";
    }

    /**
     * method to print a task that was successfully added.
     * @param task the added one.
     * @param size of the list.
     */
    public static String addedTask(Task task, int size) {
        return "Got it. I've added this task:\n" + task
                + "\n Now you have " + size + " tasks in the list.";
    }

    public static String prioritzeTask(Task task) {
        return "Yay I have prioritized this task:\n" + task;
    }

    public static String doneTask(Task task) {
        return "Nice! This task is marked as done: \n" + task;
    }

    /**
     * method to print a deleted task.
     * @param task that was deleted.
     */
    public static String deletedTask(Task task) {
        return "Meimei will forget about this task:\n" + task;
    }
}
