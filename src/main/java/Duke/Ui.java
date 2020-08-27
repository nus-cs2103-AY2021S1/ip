package Duke;

import Duke.Exception.*;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {

    /**
     * UI line divider
     */
    public static final String DIVIDER = "----------------------------------------\n";

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

    public void lineDivider() {
        out.println(DIVIDER);
    }

    public String readLine() {
        String input = in.nextLine();
        return input;
    }

    public void show(String... msg) {
        for (String m : msg) {
            out.println(m.replace("\n", LS));
        }
    }

    public void showIntro() {
        show(DIVIDER, GREET, DIVIDER);
    }

    public void showFarewell() {
        show(DIVIDER, FAREWELL, DIVIDER);
    }

    public void showError(DukeException e) {
        show(e.toString());
    }

    public void showAdd(Task task, TaskList tasklist) {
        show("Got it. I've added this task:\n"
                , task.toString()
                , "\nNow you have " + tasklist.getNumOfTask() + " tasks in the list.\n");
    }

    public void showDone(Task task) {
        show("Nice! I've marked this task as done:\n", task + "\n");
    }

    public void showDelete(Task task, TaskList tasklist) {
        String taskMsg = "Deleted: " + task + "\n";
        show("Noted. I've removed this task:\n"
                , task.toString()
                , "\nNow you have " + tasklist.getNumOfTask() + " tasks in the list.\n");
    }

    public void showFileError() {
        show("Update file failed");
    }

}
