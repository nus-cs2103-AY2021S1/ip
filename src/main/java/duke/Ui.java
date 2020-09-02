package duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import duke.exception.DukeException;

/**
 * Ui deals with interactions with the user.
 */
public class Ui {

    /**
     * Show user input message(s).
     * @param message  Message(s) to be shown
     */
    public static String show(String ... message) {
        String response = "";
        for (String m : message) {
            response += m;
        }
        return response;
    }

    /**
     * Introduction message
     */
    public static final String GREET = "\nHello! I'm Rose\nWhat can I do for you? <3\n";

    /**
     *  Farewell message
     */
    public static final String FAREWELL = "Bye. Hope to see you again soon!\n";

    private static final String LS = System.lineSeparator();

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Initiate Ui
     * @param in   InputStream
     * @param out  PrintStream
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Read use input.
     * @return String input of user
     */
    public String readLine() {
        String input = in.nextLine();
        return input;
    }

    /**
     * Show intro message.
     */
    public static String showIntro() {
        return show(GREET);
    }

    /**
     * Show farewell message.
     */
    public static String showFarewell() {
        return show(FAREWELL);
    }

    /**
     * Show error message.
     * @param e  Exception shown
     */
    public void showError(DukeException e) {
        show(e.toString());
    }

    /**
     * Show add message.
     * @param task      Task added
     * @param tasklist  TaskList which Task is stored
     */
    public static String showAdd(Task task, TaskList tasklist) {
        return show("Got it. I've added this task:\n",
                task.toString(),
                "\nNow you have " + tasklist.getNumOfTask() + " tasks in the list.\n");
    }

    /**
     * Show done message.
     * @param task Task done
     */
    public static String showDone(Task task) {
        return show("Nice! I've marked this task as done:\n", task + "\n");
    }

    /**
     * Show delete message.
     * @param task      Task added
     * @param tasklist  TaskList which Task is stored
     */
    public static String showDelete(Task task, TaskList tasklist) {
        String taskMsg = "Deleted: " + task + "\n";
        return show("Noted. I've removed this task:\n",
                task.toString(),
                "\nNow you have " + tasklist.getNumOfTask() + " tasks in the list.\n");
    }

    /**
     * Show update file failure.
     */
    public void showFileError() {
        show("Update file failed");
    }

    public static String showTaskFound(String input, String tasksFound) {
        return show("Here are the matching tasks in your list:\n", tasksFound + "\n");
    }

    public static String showNoTaskFound(String input) {
        return show("Sorry. No tasks found containing " + input + ".\n");
    }

}
