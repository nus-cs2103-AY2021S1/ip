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
    static final String HELP_INTRO = "Here are the list of commands I support!";
    static final String[] COMMANDS = {"list", "bye", "todo", "deadline", "event", "done", "find", "delete", "help"};
    static final String LIST_HELP = "To list out all existing tasks, type 'list'.";
    static final String BYE_HELP = "To exit the chat bot, type 'bye'.";
    static final String TODO_HELP = "To add a new Todo, type 'todo <description>'.";
    static final String DEADLINE_HELP = "To add a new Deadline, type 'deadline <description> /by <yyyy-MM-dd HH:mm>'.";
    static final String EVENT_HELP = "To add a new Event, type 'event <description> /at <yyyy-MM-dd HH:mm>'.";
    static final String DONE_HELP = "To mark a task as done, type 'done <taskId>'.";
    static final String FIND_HELP = "To find all items containing a keyword in its description, type 'find <keyword>'.";
    static final String DELETE_HELP = "To delete a task, type 'delete <taskId>'.";

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

        return out.toString().trim();
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
     * Returns a reply for the command help.
     *
     * @return chat bot reply.
     */
    public String replyHelp() {
        StringBuilder commands = new StringBuilder();
        for (String command : Ui.COMMANDS) {
            commands.append(String.format("%s, ", command));
        }
        return this.botReply(Ui.HELP_INTRO, commands.toString().trim(), Ui.LIST_HELP, Ui.BYE_HELP,
                Ui.TODO_HELP, Ui.DEADLINE_HELP, Ui.EVENT_HELP, Ui.DONE_HELP, Ui.FIND_HELP, Ui.DELETE_HELP);
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
