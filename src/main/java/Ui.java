import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

/**
 * Ui class deals with interactions with the user
 */
public class Ui {
    private static final String DIVIDER = "____________________________________________________________";
    private static final String LINE_SEPARATOR = System.lineSeparator();
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
     * @param in
     * @param out
     */
    Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showSearchResults(String result) {
        if (result.isBlank()) {
            out.println("No matching tasks, sorry");
        } else {
            out.println("Here are the matching tasks in your list:");
            out.println(result);
        }
    }

    private boolean shouldIgnore(String line) {
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
        while(shouldIgnore(commandLine)) {
            commandLine = in.nextLine();
        }
        return commandLine;
    }

    /**
     * Shows the welcome message
     */
    public void showWelcome() {
        showLine();
        out.println((
                " ____        _\n" +
                "|  _ \\ _   _| | _____\n" +
                "| | | | | | | |/ / _\\\n" +
                "| |_| | |_| |   <  __/\n" +
                "|____/ \\__,_|_|\\_\\___|\n" +
                "Hello! I'm Thuya\n" +
                "What may I do for you, sir/madam?")
        );
        showLine();
    }

    /**
     * Shows the message for a successful deletion of a task
     * @param task
     * @param taskList
     */
    public void showDelete(Task task, TaskList taskList) {
        out.println(
            "Noted. I've removed this task:\n" +
            task.toString() + "\n" +
            "Now you have "+ taskList.size() +" tasks in the list."
        );
    }

    /**
     * Prints a successful adding of task message
     * @param task
     * @param taskList
     */
    public void showAdd(Task task, TaskList taskList) {
        out.println(
            "Got it. I've added this task:\n" +
            task.toString() + "\n" +
            "Now you have "+ taskList.size() + " tasks in the list."
        );
    }

    /**
     * Shows the exit message when user is finished with the program
     */
    public void showBye() {
        out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a duke exception message
     * @param dukeException
     */
    public void showException(DukeException dukeException) {
        out.println(dukeException.getMessage());
    }

    /**
     * Prints the task marked as done
     * @param task
     */
    public void showDone(Task task) {
        out.println(
            "Nice! I've marked this task as done:\n" +
             task.toString()
        );
    }

    /**
     * Shows the list of tasks and related information that the user currently has
     * @param taskList
     */
    public void showTaskList(TaskList taskList) {
        out.println((taskList.toString()));
    }
}
