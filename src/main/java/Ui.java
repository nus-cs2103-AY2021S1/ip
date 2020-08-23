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
     */
    public void format(String input) {
        System.out.println(line + "\n" + input + "\n" + line);
    }

    /**
     * Greets the user with an opening message.
     */
    public void greet() {
        format("Hello! I'm\n" + logo + "\n" +
                "What can I do for you?");
    }

    /**
     * Pattern for printing duke exception messages.
     * @param ex DukeException
     */
    public void throwDukeException(DukeException ex) {
        format(ex.getMessage());
    }

    /**
     * Prints message upon a new task added.
     * @param task task to be added.
     * @param size current number of tasks in tasklist.
     */
    public void addTask(Task task, int size) {
        format("Got it. I've added this task:\n" + task +  "\n"
            + "Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints completed task upon DONE Command.
     * @param task completed task.
     */
    public void displayCompletedTask(Task task) {
        format("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Prints deleted task upon DELETE Command.
     * @param task task to be deleted.
     * @param size current number of tasks in tasklist.
     */
    public void displayDeletedTask(Task task, int size) {
        format("Noted. I've removed this task:\n" + task + "\n"
            + "Now you have " + size + " tasks in the list.");
    }

    public void displayTasksWithKeyword(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        int len = tasks.size();
        if (len == 0) {
            sb.append("No tasks!");
            format(sb.toString());
            return;
        }
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < len; i++) {
            sb.append(i + 1 + "." + tasks.get(i) + "\n");
        }
        format(sb.toString());
    }

    /**
     * Returns the scanner used in the UI.
     * @return Scanner.
     */
    public Scanner getScanner() {
        return this.sc;
    }
}
