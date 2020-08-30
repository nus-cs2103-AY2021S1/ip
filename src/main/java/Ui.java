import java.util.List;
import java.util.Scanner;

/**
 * Represents the user interface to deal with interactions with the user.
 */
public class Ui {
    private final String line = "----------------------";
    private final String logo = " ____        _        \n"
                                + "|  _ \\ _   _| | _____ \n"
                                + "| | | | | | | |/ / _ \\\n"
                                + "| |_| | |_| |   <  __/\n"
                                + "|____/ \\__,_|_|\\_\\___|\n";
    private Scanner sc = new Scanner(System.in);

    /**
     * Obtains input from the user.
     * @return User input.
     */
    public String getInput() {
        return sc.nextLine();
    }

    /**
     * Provide a format to all responses by Duke.
     * @param input response to be given.
     * @return Duke response.
     */
    public String format(String input) {
        return "Duke: " + input;
    }

    /**
     * Returns/Greets the user with an opening message.
     * @return Returns a greeting string.
     */
    public String greet() {
        return format("Hello! I'm\n" + logo + "\n"
                + "What can I do for you?");
    }

    /**
     * Returns a string pattern for displaying duke exception messages.
     * @param ex DukeException.
     * @return Returns a string representation of a duke exception.
     */
    public String throwDukeException(DukeException ex) {
        return format(ex.getMessage());
    }

    /**
     * Returns/Prints message upon a new task added.
     * @param task task to be added.
     * @param size current number of tasks in tasklist.
     * @return Returns a string message when a task is added.
     */
    public String addTask(Task task, int size) {
        return format("Got it. I've added this task:\n" + task + "\n"
            + "Now you have " + size + " tasks in the list.");
    }

    /**
     * Returns/Prints completed task upon DONE Command.
     * @param task completed task.
     * @return Returns a string message when a task is done.
     */
    public String displayCompletedTask(Task task) {
        return format("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Returns/Prints deleted task upon DELETE Command.
     * @param task task to be deleted.
     * @param size current number of tasks in tasklist.
     * @return Returns a string message when a task is deleted.
     */
    public String displayDeletedTask(Task task, int size) {
        return format("Noted. I've removed this task:\n" + task + "\n"
            + "Now you have " + size + " tasks in the list.");
    }

    /**
     * Returns/Prints tasks according to LIST/FIND Command.
     * @param tasks tasks to be printed.
     * @return Returns a string message of all the tasks.
     */
    public String displayTasksWithCommand(List<Task> tasks, String command) {
        StringBuilder sb = new StringBuilder();
        int len = tasks.size();
        if (len == 0) {
            sb.append("No tasks!");
            return format(sb.toString());
        }
        sb.append("Here are the" + (command.equals("find") ? " matching " : " ")
                + "tasks in your list:\n");
        for (int i = 0; i < len; i++) {
            sb.append(i + 1 + "." + tasks.get(i) + "\n");
        }
        return format(sb.toString());
    }

    /**
     * Returns the scanner used in the UI.
     * @return Scanner.
     */
    public Scanner getScanner() {
        return this.sc;
    }
}
