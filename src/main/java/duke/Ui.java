package duke;

import java.util.Scanner;

/**
 * The Ui class deals with the interactions with the user.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructor for a Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Greets the user.
     */
    public String greetUser() {
        return ("Hello, I'm Bo-Chan!\n" + "How may I help you?");
    }

    /**
     * Says bye to the user.
     */
    public String exit() {
        return "Bye, see you soon!";
    }

    /**
     * Scans the input of the user.
     * 
     * @return a String object of the user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints the message when a task is successfully added into the tasklist.
     * 
     * @param task Task added into the tasklist.
     * @param taskList tasklist of the user.
     */
    public String showAddition(Task task, TaskList taskList) {
        return ("Added this task to your list:\n" + task + "\n" + showTaskTotal(taskList));
    }

    /**
     * Prints the message when a task is successfully completed.
     * 
     * @param task Task completed.
     */
    public String showCompletion(Task task) {
        return ("Nice! I've marked this task as done: \n" + task);
    }

    /**
     * Prints the message when a task is successfully deleted.
     * 
     * @param task Task deleted.
     * @param taskList tasklist of the user.
     */
    public String showDeletion(Task task, TaskList taskList) {
        return ("Noted. Removed task: \n" + task + "\n" + showTaskTotal(taskList));
    }

    /**
     * Prints the message when the list is empty.
     * 
     * @return a String object of an empty list message.
     */
    public String showEmptyList() {
        return "No tasks in your list yet";
    }

    /**
     * Prints the message when there is an error loading the user's file.
     * 
     * @return a String object of a loading error message.
     */
    public String showLoadingError() {
        return "Error loading files";
    }

    /**
     * Prints the error message.
     * 
     * @param message error message.
     * @return a String object of a error message.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Prints the message informing the user of the number of tasks in list.
     *
     * @param taskList tasklist of the user.
     * @return a String object of the number of tasks in list.
     */
    public String showTaskTotal(TaskList taskList) {
        int total = taskList.getSize();
        return ("You now have " + Integer.toString(total) + " task(s) in the list.");
    }

    /**
     * Prints a message on current tasks that are not overdue.
     *
     * @return a String object of the current tasks that are not overdue.
     */
    public String showNotOverdue() {
        return ("These are your current tasks:" + "\n");
    }

    /**
     * Prints a message on current tasks that are overdue.
     *
     * @return a String object of the current tasks that are overdue.
     */
    public String showOverdue() {
        return ("These are your overdue tasks:" + "\n");
    }
}
