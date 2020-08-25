package duke;

import java.util.Scanner;

/**
 * User interaction class that handles how the bot replies.
 */
public class Ui {
    /**
     * The scanner to take in inputs from the user.
     */
    private final Scanner sc;

    /**
     * The messages that the chat bot sends out.
     */
    static final String DIVIDE = "_____________________________________________________________";
    static final String WELCOME_MESSAGE = "Hello! I'm duke.Duke\nWhat can I do for you?";
    static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    static final String LIST_MESSAGE = "Here are the tasks in your list:\n";
    static final String DONE_MESSAGE = "Nice! I've marked this task as done:\n";
    static final String TASK_ADDED_MESSAGE = "Got it. I've added this task:\n";
    static final String DELETE_MESSAGE = "Noted. I've removed this task:\n";
    static final String LOADING_ERROR_MESSAGE = "Something went wrong when loading previously saved tasks!\n" +
            " Starting with an empty tasks list instead...";

    /**
     * Initializes a new UI object.
     */
    Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints a message to the user in the given format.
     *
     * @param message the message to send.
     */
    public void botReply(String message) {
        System.out.printf("%s\n%s\n%s\n", Ui.DIVIDE, message, Ui.DIVIDE);
    }

    /**
     * Prints an error message if there is a problem with the UI.
     */
    public void showLoadingError() {
        this.botReply(Ui.LOADING_ERROR_MESSAGE);
    }

    /**
     * Prints a welcome message when starting the chat bot.
     */
    public void showWelcome() {
        this.botReply(Ui.WELCOME_MESSAGE);
    }

    /**
     * prints a goodbye message when exiting the chat bot.
     */
    public void showGoodbye() {
        this.botReply(Ui.EXIT_MESSAGE);
    }

    /**
     * Appends the standard list message before printing out the list of tasks.
     *
     * @param content list of tasks.
     */
    public void replyList(String content) {
        this.botReply(Ui.LIST_MESSAGE + content);
    }

    /**
     * Appends the standard done message before printing out the done task.
     *
     * @param content the task that was just marked as done.
     */
    public void replyDone(String content) {
        this.botReply(Ui.DONE_MESSAGE + content);
    }

    /**
     * Appends the standard added task message before printing out the added task.
     *
     * @param content the added task.
     */
    public void replyAdd(String content) {
        this.botReply(Ui.TASK_ADDED_MESSAGE + content);
    }

    /**
     * Appends the standard delete message before printing out the deleted task.
     *
     * @param content the deleted task.
     */
    public void replyDelete(String content) {
        this.botReply(Ui.DELETE_MESSAGE + content);
    }

    /**
     * Prints out the error message.
     *
     * @param message error message
     */
    public void showError(String message) {
        this.botReply(message);
    }

    /**
     * Reads the next line of the user's input.
     *
     * @return User's input.
     */
    public String readCommand() {
        return this.sc.nextLine();
    }
}
