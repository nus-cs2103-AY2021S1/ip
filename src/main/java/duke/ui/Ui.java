package duke.ui;

/**
 * User interaction class that handles how the bot replies.
 */
public class Ui {

    /**
     * The messages that the chat bot sends out.
     */
    static final String WELCOME_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    static final String LIST_MESSAGE = "Here are the tasks in your list:";
    static final String DONE_MESSAGE = "Nice! I've marked this task as done:";
    static final String TASK_ADDED_MESSAGE = "Got it. I've added this task:";
    static final String DELETE_MESSAGE = "Noted. I've removed this task:";
    static final String TASK_NOT_FOUND_MESSAGE = "No matching tasks found!";
    static final String TASK_FOUND_MESSAGE = "Here are the matching tasks in your list:";
    static final String LOADING_ERROR_MESSAGE = "Something went wrong when loading previously saved tasks!\n"
            + "Starting with an empty tasks list instead...";

    /**
     * Formats chat bot replies and returns them as a string.
     *
     * @param message the message to send.
     * @return chat bot reply.
     */
    public String botReply(String... message) {
        StringBuilder out = new StringBuilder();

        for (String s : message) {
            out.append(s).append("\n");
        }

        return out.toString();
    }

    /**
     * Returns a reply formatted error message if there is a problem with the UI.
     *
     * @return chat bot reply.
     */
    public String showLoadingError() {
        return this.botReply(Ui.LOADING_ERROR_MESSAGE);
    }

    /**
     * Returns a welcome message as a string.
     *
     * @return chat bot reply.
     */
    public String showWelcome() {
        return this.botReply(Ui.WELCOME_MESSAGE);
    }

    /**
     * Returns a reply formatted goodbye message as a string.
     *
     * @return chat bot reply.
     */
    public String showGoodbye() {
        return this.botReply(Ui.EXIT_MESSAGE);
    }

    /**
     * Returns a reply formatted standard list message with the list of tasks as a string.
     *
     * @param content list of tasks.
     * @return chat bot reply.
     */
    public String replyList(String content) {
        return this.botReply(Ui.LIST_MESSAGE, content);
    }

    /**
     * Returns a reply formatted standard done message with the done task as a string.
     *
     * @param content the task that was just marked as done.
     * @return chat bot reply.
     */
    public String replyDone(String content) {
        return this.botReply(Ui.DONE_MESSAGE, content);
    }

    /**
     * Returns a reply formatted standard added task message with the added task as a string.
     *
     * @param content the added task.
     * @return chat bot reply.
     */
    public String replyAdd(String content) {
        return this.botReply(Ui.TASK_ADDED_MESSAGE, content);
    }

    /**
     * Returns a reply formatted standard delete message with the deleted task as a string.
     *
     * @param content the deleted task.
     * @return chat bot reply.
     */
    public String replyDelete(String content) {
        return this.botReply(Ui.DELETE_MESSAGE, content);
    }

    /**
     * Returns a reply formatted standard task not found message as a string.
     *
     * @return chat bot reply.
     */
    public String replyNoTasksFound() {
        return this.botReply(Ui.TASK_NOT_FOUND_MESSAGE);
    }

    /**
     * Returns a reply formatted standard found message with the found task(s) as a string.
     *
     * @param content task(s).
     * @return chat bot reply.
     */
    public String replyFoundTasks(String content) {
        return this.botReply(Ui.TASK_FOUND_MESSAGE, content);
    }

    /**
     * Returns a reply formatted error message as a string.
     *
     * @param message error message
     * @return chat bot reply.
     */
    public String showError(String message) {
        return this.botReply(message);
    }
}
