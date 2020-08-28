package duke;

import java.util.Scanner;

/**
 * Class to initiate the Ui object. Contains various methods to handle different duke.commands.
 */
public class Ui {

    private static final String DIVIDER = "__________________________________________________________";
    private final Scanner sc = new Scanner(System.in);

    private void messageFormatter(String word) {
        System.out.println(DIVIDER);
        System.out.println(word);
        System.out.println(DIVIDER + "\n");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints the welcome message.
     *
     * @param taskList Prints out the current tasks in list.
     */
    public void welcomeMessage(String taskList) {
        messageFormatter("Hello! I'm Duke! Welcome back!\n"
                + "Here are the tasks in your list:\n" + taskList);
    }

    public void byeMessage() {
        messageFormatter("Bye. Hope to see you again soon!");
    }

    public void listMessage(String taskList) {
        messageFormatter("Here are the tasks in your list:\n" + taskList);
    }

    public void errorMessage(String error) {
        messageFormatter(error);
    }

    /**
     * Prints the done message.
     *
     * @param message Prints out the current task that is completed.
     */
    public void markAsDoneMessage(String message) {
        messageFormatter("Nice! I've marked this task as done:\n" + message);
    }

    /**
     * Prints the delete message.
     *
     * @param task Prints out the current deleted task.
     * @param size Number of tasks in the list.
     */
    public void deleteMessage(String task, int size) {
        messageFormatter("Noted. I've removed this task:\n" + task
                + "\nNow you have " + size + " tasks in the list.");
    }

    /**
     * Prints the the added task message.
     *
     * @param task Prints out the added task in list.
     * @param size Number of tasks in the list.
     */
    public void taskMessage(String task, int size) {
        messageFormatter("Got it. I've added this task:\n" + task
                + "\nNow you have " + size + " tasks in the list.");
    }

    /**
     * Prints the task that suits the input keyword message.
     *
     * @param taskList Prints out the task that suits the input keyword.
     * @param size Number of tasks in the list.
     */
    public void findTaskMessage(String taskList, int size) {
        if (size == 0) {
            messageFormatter("There are not matching task in your list!");
        } else {
            messageFormatter("There are " + size + " matching tasks in your list:\n" + taskList);
        }
    }

}
