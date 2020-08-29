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
    public void greet() {
        System.out.println("Hello, I'm Eggy!\n" + "How may I help you?");
    }

    /**
     * Says bye to the user.
     */
    public void exit() {
        System.out.println("Bye, see you soon!");
        sc.close();
    }

    /**
     * Scans the input of the user.
     * @return a String object of the user input.
     */
    public String readCommand() {
        String command = sc.nextLine();
        return command;
 
    }

    /**
     * Prints the message when a task is successfully added into the tasklist.
     * @param task Task added into the tasklist.
     * @param taskList tasklist of the user.
     */
    public void showAddition(Task task, TaskList taskList) {
        System.out.println("Added this task to your list:\n" + task);
        showTaskTotal(taskList);
    }

    /**
     * Prints the message when a task is successfully completed.
     * @param task Task completed.
     */
    public void showCompletion(Task task) {
        System.out.println("Nice! I've marked this task as done: \n" + task);
    }

    /**
     * Prints the message when a task is successfully deleted.
     * @param task Task deleted.
     * @param taskList tasklist of the user.
     */
    public void showDeletion(Task task, TaskList taskList) {
        System.out.println("Noted. Removed task: \n" + task);
        showTaskTotal(taskList);
    }

    /**
     * Prints the message when the list is empty.
     */
    public void showNoTask() {
        System.out.println("No tasks in your list yet");
    }

    /**
     * Prints the message when there is an error loading the user's file.
     */
    public void showLoadingError() {
        System.out.println("Error loading files");
    }

    /**
     * Prints the error message.
     * @param message error message.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Prints the message informing the user of the number of tasks in list.
     * @param taskList tasklist of the user.
     */
    public void showTaskTotal(TaskList taskList) {
        int total = taskList.getSize();
        System.out.printf("You now have %d task%s in the list.\n", total, total > 1 ? "s" : "");
    }
}
