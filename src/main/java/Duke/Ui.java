package Duke;

import Duke.Exception.*;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Ui deals with interactions with the user.
 */
public class Ui {

    /**
     * UI line divider
     */
    public static final String DIVIDER = "------------------------------------------------\n";

    /**
     * Introduction message
     */
    public static final String GREET =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n"
                    + DIVIDER
                    + "Hello! I'm Duke\nWhat can I do for you?\n";

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

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Print line divider.
     */
    public void lineDivider() {
        out.println(DIVIDER);
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
     * Show user input message(s).
     * @param msg  Message(s) to be shown
     */
    public void show(String... msg) {
        for (String m : msg) {
            out.println(m.replace("\n", LS));
        }
    }

    /**
     * Show intro message.
     */
    public void showIntro() {
        show(DIVIDER, GREET, DIVIDER);
    }

    /**
     * Show farewell message.
     */
    public void showFarewell() {
        show(DIVIDER, FAREWELL, DIVIDER);
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
    public void showAdd(Task task, TaskList tasklist) {
        show("Got it. I've added this task:\n"
                , task.toString()
                , "\nNow you have " + tasklist.getNumOfTask() + " tasks in the list.\n");
    }

    /**
     * Show done message.
     * @param task Task done
     */
    public void showDone(Task task) {
        show("Nice! I've marked this task as done:\n", task + "\n");
    }

    /**
     * Show delete message.
     * @param task      Task added
     * @param tasklist  TaskList which Task is stored
     */
    public void showDelete(Task task, TaskList tasklist) {
        String taskMsg = "Deleted: " + task + "\n";
        show("Noted. I've removed this task:\n"
                , task.toString()
                , "\nNow you have " + tasklist.getNumOfTask() + " tasks in the list.\n");
    }

    /**
     * Show update file failure.
     */
    public void showFileError() {
        show("Update file failed");
    }

    public void showTaskFound(String input, String tasksFound) {
        show("Here are the matching tasks in your list:\n", tasksFound + "\n");
    }

    public void showNoTaskFound(String input) {
        show("Sorry. No tasks found containing " + input + ".\n");
    }

}
