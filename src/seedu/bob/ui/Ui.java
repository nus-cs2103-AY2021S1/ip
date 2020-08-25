package seedu.bob.ui;

import seedu.bob.common.Messages;
import seedu.bob.data.task.Task;
import seedu.bob.data.task.Tasklist;
import seedu.bob.exceptions.BobException;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    /** Divider to be display on UI */
    public static final String DIVIDER =
            "====================================================================="
                    + "========================================================\n";

    /** A platform independent line separator. */
    private static final String LS = System.lineSeparator();

    private final Scanner in;
    private final PrintStream out;

    public Ui () {
        this(System.in, System.out);
    }

    public Ui (InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String readCommand() {
        return in.nextLine();
    }

    public void showDivider() {
        out.println(DIVIDER);
    }

    public void showToUser(String... message) {
        for (String m : message) {
            out.println(m.replace("\n", LS));
        }
    }

    public void showIntroMessage() {
        showToUser(
                DIVIDER,
                Messages.INTRO,
                DIVIDER
        );
    }

    public void showExitMessage() {
        showToUser(
                Messages.OUTRO
        );
    }

    public void showError(BobException e) {
        showToUser(e.toString());
    }

    public void showAddMessage(Task task, Tasklist tasks) {
        // Starting line
        String start = Messages.ADDMSG;
        // Task
        String taskMsg = task + "\n";
        // Ending line
        String end = "Currently you have " + tasks.getListSize() + " tasks in your list.\n";
        showToUser(start, taskMsg, end);
    }

    public void showDoneMessage(Task task) {
        String taskMsg = task + "\n";
        showToUser(Messages.DONEMSG, taskMsg);
    }

    public void showDeleteMessage(Task task) {
        String taskMsg = "Deleted: " + task + "\n";
        showToUser(Messages.DELETEMSG, taskMsg);
    }

    public void showLoadingError() {
        showToUser(Messages.LOADINGERROR);
    }

    public void showUpdatingError() {
        showToUser(Messages.UPDATEERROR);
    }
}
