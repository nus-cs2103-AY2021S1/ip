package duke;

import java.util.Scanner;

import exception.DukeException;
import task.Task;

/**
 * Class that handles interactions with the user
 */
public class Ui {
    private String line = "____________________________________________________________";
    private String welcomeMessage = "Hello! I'm Duke\n What can I do for you?";
    private String goodbyeMessage = "Bye! Message me anytime!!";
    private Scanner sc = new Scanner(System.in);

    public void showLine() {
        System.out.println(this.line);
    }

    /**
     * Show welcome method to print the welcome message
     */
    public void showWelcome() {
        System.out.println(line);
        System.out.println(this.welcomeMessage);
        System.out.println(line);
    }

    public void showGoodbye() {
        System.out.println(this.goodbyeMessage);
    }

    /**
     * Method which prints out when a task is completed
     * @param task Task to show
     */
    public void showDone(Task task) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task);
    }

    /**
     * Print the task that has been added to taskList
     * @param task Task to be added
     * @param size Number of tasks
     */
    public void showAdd(Task task, int size) {
        System.out.println("Added task.Task!");
        System.out.println(task);
        showTaskQty(size);
    }

    /**
     * Method to print the number of remaining tasks
     * @param size Number of remaining tasks
     */
    public void showTaskQty(int size) {
        System.out.println(String.format("You have %s remaining tasks left, work on them soon!", size));
    }

    /**
     * Prints out the deleted task
     * @param task Task to be deleted
     * @param size Number of tasks
     */
    public void showDelete(Task task, int size) {
        System.out.println("Deleting task.Task: ");
        System.out.println(task);
        showTaskQty(size);
    }

    public void showError(String errMsg) {
        System.out.println(errMsg);
    }

    /**
     * Prints out tasks in taskList
     * @param taskList tasklist containing task objects
     */
    public void displayTaskList(TaskList taskList) {
        System.out.println("Here are your current tasks:");
        for (int i = 0; i < taskList.getTaskList().size(); i++) {
            System.out.println(i + 1 + "." + taskList.getTaskList().get(i));
        }
    }

    /**
     * Uses the Java Scanner to read User Input and trims it
     *
     * @return String containing the trimmed user input
     * @throws DukeException When the user input given is invalid
     */
    public String readCommand() throws DukeException {
        String line = sc.nextLine();
        if (line != null) {
            return line.trim();
        } else {
            throw new DukeException("Please type in a command!");
        }
    }

    /**
     * Prints out contents of the filtered taskList
     * @param filteredTaskList taskList object
     */
    public void displayMatchingTasks(TaskList filteredTaskList) {

        if (filteredTaskList.getSize() > 0) {
            System.out.println("Here are the matching tasks in your list");
            for (int i = 0; i < filteredTaskList.getTaskList().size(); i++) {
                System.out.println(i + 1 + "." + filteredTaskList.getTaskList().get(i));
            }
        } else {
            System.out.println("You have no matching tasks!");
        }
    }
}
