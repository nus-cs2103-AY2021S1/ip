package duke.main;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class that interacts with the user.
 */
public class UI {
    private static Scanner sc;

    /**
     * Initialises the UI class.
     */
    public UI() {
        sc = new Scanner(System.in);
    }

    private void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads in the user's input.
     *
     * @return Returns the trimmed version of the user's input
     */
    public String readCommand() {
        return sc.nextLine().trim();
    }

    private void showCmd(String s) {
        showLine();
        System.out.println(s);
        showLine();
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        showCmd(" Hello! I'm Duke!");
    }

    /**
     * Displays the goodbye message to the user.
     */
    public void showGoodbye() {
        showCmd(" Bye. Hope to see you again soon!");
    }

    /**
     * Displays the error message to the user.
     *
     * @param error The error that was thrown
     */
    public void showError(String error) {
        showCmd(error);
    }

    /**
     * Displays the added task message to the user.
     *
     * @param task The task that was added
     * @param n    The size of the TaskList after the task was added
     */
    public void addTask(String task, int n) {
        String s = " Got it. I've added this task:\n   " + task + "\n";
        showCmd(s.concat(displayListSize(n)));
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param taskList The TaskList of the user
     */
    public void displayList(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            showCmd("You have no tasks left! Good job my child!");
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
            showCmd(s);
        }
    }

    /**
     * Displays the list of tasks that contains the matching keyword to the user.
     *
     * @param taskList The TaskList containing tasks with the matching keyword
     */
    public void displayMatchingList(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            showCmd(" I've found no matching tasks with the keyword! T_T\n Try again!");
        } else {
            String s = "";
            if (taskList.size() == 1) {
                s = s.concat(" Here is the matching task in your list:\n");
            } else {
                s = s.concat(" Here are the matching tasks in your list:\n");
            }
            for (int i = 0; i < taskList.size(); i++) {
                s = s.concat(" " + (i+1) + "." + taskList.get(i) + "\n");
            }
            s = s.substring(0, s.length() - 1);
            showCmd(s);
        }
    }

    /**
     * Displays the size of the TaskList to the user.
     *
     * @param n The size of the TaskList
     * @return Returns the String message to be concatenated with the display message.
     */
    public String displayListSize(int n) {
        if (n == 0) {
            return (" Great job son! You're left with no more tasks!");
        } else if (n == 1) {
            return String.format(" Now you have %d task in the list.", n);
        } else {
            return String.format(" Now you have %d tasks in the list.", n);
        }
    }

    /**
     * Displays the start saving message to the user.
     */
    public void startSaving() {
        System.out.println("Saving...");
    }

    /**
     * Displays the save was successful message to the user.
     */
    public void saveSuccess() {
        System.out.println("Saved successfully!");
    }

    /**
     * Displays the start loading message to the user.
     */
    public void startLoading() {
        System.out.println("Fetching old data...");
    }

    /**
     * Displays the load was successful message to the user.
     */
    public void loadSuccess() {
        System.out.println("Data successfully loaded! ^^");
    }

    /**
     * Displays the task was successfully done message to the user.
     *
     * @param task The task that was successfully completed
     */
    public void doneTask(String task) {
        showCmd(String.format("Great job! I'll mark '%s' as done for you. ^^", task));
    }

    /**
     * Displays the task was successfully deleted message to the user.
     *
     * @param task The task that was successfully deleted
     * @param n    The number of remaining tasks
     */
    public void deleteTask(String task, int n) {
        String s = " Noted. I've removed this task:\n  " + task + "\n";
        showCmd(s.concat(displayListSize(n)));
    }

    /**
     * Displays the help message to the user.
     */
    public void showHelp() {
        showCmd("Here are the list of commands you can use:\n" +
                "help\nlist\nsave\nbye\ntodo 'TASK'\ndeadline 'TASK' /by 'dd/MM/yyyy HH:mm'\n" +
                "event 'TASK' /at 'dd/MM/yyyy HH:mm'\ndone 'n'\ndelete 'n'");
    }
}
