package duke;

import java.util.List;
import java.util.Scanner;

import duke.task.Task;


/**
 * Interacts with the user by reading user inputs and outputting the
 * appropriate responses.
 */
public class Ui {
    private static final String DISPLAY_LINE = "______________________________________________________";
    private static final String DISPLAY_LINEINDENT = "    ";
    private static final String DISPLAY_LISTINDENT = "       ";
    private static final String DISPLAY_TEXTINDENT = "     ";
    private Scanner sc;

    /**
     * Creates an instance of UI with a scanner to read in
     * user inputs.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Displays a welcome message when the user first starts up the program.
     */
    public void displayWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(String.format("Hello from\n%s\nWhat can I do for you?",
                logo));
    }

    /**
     * Reads in user input using the scanner.
     * @return the user input in the form of a string
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays a formatted message for the user.
     * @param message the message displayed to the user
     */
    public void printMessage(String message) {
        System.out.println(String.format("%s%s\n%s%s\n%s%s", DISPLAY_LINEINDENT, DISPLAY_LINE,
                DISPLAY_TEXTINDENT, message, DISPLAY_LINEINDENT, DISPLAY_LINE));
    }

    /**
     * Lists down all the tasks the user has
     * @param taskList the current tasks the user has
     */
    public void listTasks(TaskList taskList) {
        String output = "Here are the tasks in your list:\n";
        int taskLen = taskList.getCount();
        for (int i = 0; i < taskLen; i++) {
            output += String.format("%s%d. %s", DISPLAY_TEXTINDENT, i + 1, taskList.getTasks().get(i));
            if (i != taskLen - 1) {
                output += "\n";
            }
        }
        printMessage(output);
    }

    /**
     * Informs the user when he has successfully marked a task as complete.
     * @param task the task completed by user
     */
    public void completeSuccess(Task task) {
        printMessage(String.format("Nice! I've marked this task as done:\n%s%s",
                DISPLAY_LISTINDENT, task));
    }

    /**
     * Informs the user when he has successfully deleted a task, along with a count of current tasks.
     * @param task the task deleted by user
     * @param taskCount the current number of tasks the user has
     */
    public void deleteSuccess(Task task, int taskCount) {
        String message = String.format("Noted. I've removed this task:\n%s%s\n%sNow you have %d %s in the list.",
                DISPLAY_LISTINDENT, task, DISPLAY_TEXTINDENT, taskCount, taskCount > 1 ? "tasks" : "task");
        printMessage(message);
    }

    /**
     * Informs the user when he has successfully added a task, along with a count of current tasks.
     * @param task the task added by user
     * @param taskCount the current number of tasks the user has
     */
    public void addSuccess(Task task, int taskCount) {
        String message = String.format("Got it. I have added this task:\n%s%s\n%sNow you have %d %s in the list.",
                DISPLAY_LISTINDENT, task, DISPLAY_TEXTINDENT, taskCount, taskCount > 1 ? "tasks" : "task");
        printMessage(message);
    }

    /**
     * Lists all the tasks the user has queried.
     * @param taskList the list of tasks matching the user query
     */
    public void listQueriedTasks(List<Task> taskList) {
        String output = "Here are the matching tasks in your list:\n";
        int taskSize = taskList.size();
        for (int i = 0; i < taskSize; i++) {
            output += String.format("%s%d. %s", DISPLAY_TEXTINDENT, i + 1, taskList.get(i));
            if (i != taskSize - 1) {
                output += "\n";
            }
        }
        printMessage(output);
    }

    /**
     * Closes the scanner after user exits the program.
     */
    public void close() {
        sc.close();
    }
}
