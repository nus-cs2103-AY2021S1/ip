import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Ui class deals with interactions with the user
 */
public class Ui {
    private static final String DIVIDER = "____________________________________________________________";
    private final Scanner in;
    private final PrintStream out;

    /**
     * Creates a UI object with the standard system in and out as its input and output
     */
    Ui() {
        this(System.in, System.out);
    }

    /**
     * Creates a UI object with the specified input and output
     * @param in Scanner
     * @param out PrintStream
     */
    Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Gets search results from taskList when searching the list
     * @param result results of search
     * @return a string of search results
     */
    public String getSearchResults(String result) {
        if (result.isBlank()) {
            return ("No matching tasks, sorry");
        } else {
            return "Here are the matching tasks in your list:\n"
                    + (result);
        }
    }

    /**
     * Gets message when tasks are saved to file successfully
     * @param taskList list of tasks from loaded file path
     * @param storage new path name
     * @return message when tasks are saved to file successfully
     */
    public String getSaveMessage(TaskList taskList, Storage storage) {
        return "Saved to "
                + storage.toString()
                + "!\nCurrent tasks are\n"
                + taskList.toString();
    }

    /**
     * Returns the message when filepath is successfully changed
     * @param taskList list of tasks from loaded file path
     * @param storage new path name
     * @return message when path is changed
     */
    public String getChangeSavePathMessage(TaskList taskList, Storage storage) {
        return "Changed save file to "
                + storage.toString()
                + ",\n"
                + taskList.toString();
    }

    /**
     * Checks when to ignore a command line
     * @param line current command line
     * @return returns true if line should be ignored, else false
     */
    public boolean shouldIgnore(String line) {
        return line.trim().isEmpty();
    }

    /**
     * Prints out the divider line
     */
    public void showLine() {
        out.println(DIVIDER);
    }

    /**
     * Prints out the input message, and asks for user input
     * @return the user input
     */
    public String readCommand() {
        String commandLine = in.nextLine();
        while (shouldIgnore(commandLine)) {
            commandLine = in.nextLine();
        }
        return commandLine;
    }

    /**
     * Shows the welcome message
     */
    public void showWelcome() {
        showLine();
        out.println(getWelcomeMessage());
        showLine();
    }

    /**
     * Returns the welcome message
     * @return the welcome message
     */
    public String getWelcomeMessage() {
        return "Hello! I'm Duke\n"
                + "What may I do for you, sir/madam?\n\n"
                + "File will be saved at default_tasks.txt, use \"load NEW_FILE_NAME\" to change it!";
    }

    /**
     * Gets the message for a successfully deletion of a task
     * @param task that was successfully deleted
     * @param taskList TaskList that task was deleted to
     * @return message when task is successfully deleted
     */
    public String getDeleteMessage(Task task, TaskList taskList) {
        return "Noted. I've removed this task:\n"
                + task.toString() + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Gets the message when task is successfully
     * @param task task that was successfully added
     * @param taskList taskList that task was added to
     * @return message that task is successfully added
     */
    public String getAddTaskMessage(Task task, TaskList taskList) {
        return "Got it. I've added this task:\n"
                + task.toString() + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Generates the exit message when user is finished with the program
     * @return the exit message
     */
    public String getByeMessage() {
        return ("Bye. Hope to see you again soon!");
    }

    /**
     * Gets the exception message when duke encounters an error
     * @param dukeException exception made by duke
     * @return the exception message
     */
    public String getExceptionMessage(DukeException dukeException) {
        return "Oops Duke ran into an error: " + dukeException.getMessage();
    }

    /**
     * gets the done task message
     * @param task task that is done
     * @return message that task is done
     */
    public String getTaskDoneMessage(Task task) {
        return "Nice! I've marked this task as done:\n"
                + task.toString();
    }

    /**
     * Prints out the message.
     * @param message message to be printed out
     */
    public void show(String message) {
        out.println(message);
    }

    /**
     * Returns the list of tasks and related information that the user currently has
     * @param taskList list of tasks
     * @return list of task in string form
     */
    public String getTaskList(TaskList taskList) {
        return taskList.toString();
    }
}
