package duke.main;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * The class that interacts with the user.
 */
public class UI {
    private static Scanner sc;

    public final static String WELCOME_MESSAGE = " Hello! I'm Duke!";

    public final static String GOODBYE_MESSAGE = " Bye. Hope to see you again soon!\n"
            + " You can now safely close the app!";

    public final static String HELP_MESSAGE = "Here are the list of commands you can use:\n"
            + "help, list, save, bye, todo 'TASK', deadline 'TASK' /by 'dd/MM/yyyy HH:mm', "
            + "event 'TASK' /at 'dd/MM/yyyy HH:mm', done 'n', delete 'n'";

    public final static String SORT_MESSAGE = "Tasks successfully sorted by date!";

    public final static String SAVE_START = "Saving...";

    public final static String SAVE_SUCCESS = "Saved successfully!";
    /**
     * Initialises the UI class.
     */
    public UI() {
        sc = new Scanner(System.in);
    }

    /**
     * Reads in the user's input.
     *
     * @return Returns the trimmed version of the user's input
     */
    public String readCommand() {
        return sc.nextLine().trim();
    }

    /**
     * Displays the error message to the user.
     *
     * @param error The error that was thrown
     */
    public String showError(String error) {
        return error;
    }

    /**
     * Displays the added task message to the user.
     *
     * @param task The task that was added
     * @param n    The size of the TaskList after the task was added
     */
    public String addTask(String task, int n) {
        String s = " Got it. I've added this task:\n   " + task + "\n";
        return s.concat(displayListSize(n));
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param taskList The TaskList of the user
     */
    public String displayList(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            return "You have no tasks left! Good job my child!";
        } else {
            String s = "";
            if (taskList.size() == 1) {
                s = s.concat(" Here is the task in your list:\n");
            } else {
                s = s.concat(" Here are the tasks in your list:\n");
            }
            for (int i = 0; i < taskList.size(); i++) {
                s = s.concat(" " + (i + 1) + "." + taskList.get(i) + "\n");
            }
            s = s.substring(0, s.length() - 1);
            return s;
        }
    }

    /**
     * Displays the list of tasks that contains the matching keyword to the user.
     *
     * @param taskList The TaskList containing tasks with the matching keyword
     */
    public String displayMatchingList(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            return "I've found no matching tasks with the keyword! T_T\nTry again!";
        } else {
            String s = "";
            if (taskList.size() == 1) {
                s = s.concat(" Here is the matching task in your list:\n");
            } else {
                s = s.concat(" Here are the matching tasks in your list:\n");
            }
            for (int i = 0; i < taskList.size(); i++) {
                s = s.concat(" " + (i + 1) + "." + taskList.get(i) + "\n");
            }
            s = s.substring(0, s.length() - 1);
            return s;
        }
    }

    /**
     * Displays the size of the TaskList to the user.
     *
     * @param n The size of the TaskList
     * @return Returns the String message to be concatenated with the display message.
     */
    public String displayListSize(int n) {
        assert n > -1 : "Error in UI code";
        if (n == 0) {
            return ("Great job! You're left with no more tasks!");
        } else if (n == 1) {
            return String.format("Now you have %d task in the list.", n);
        } else {
            return String.format("Now you have %d tasks in the list.", n);
        }
    }

    /**
     * Displays the task was successfully done message to the user.
     *
     * @param task The task that was successfully completed
     */
    public String doneTask(String task) {
        return String.format("Great job! I'll mark '%s' as done for you. ^^", task);
    }

    /**
     * Displays the task was successfully deleted message to the user.
     *
     * @param task The task that was successfully deleted
     * @param n    The number of remaining tasks
     */
    public String deleteTask(String task, int n) {
        String s = "Noted. I've removed this task:\n  " + task + "\n";
        return s.concat(displayListSize(n));
    }

}
