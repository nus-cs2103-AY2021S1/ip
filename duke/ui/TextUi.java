package duke.ui;

import duke.commands.CommandResult;
import duke.data.task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


/**
 * Text UI of the application.
 */
public class TextUi {

    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";


    private static String INDENT = "   ";
    private static String DIVIDER = "----------------------------";

    private final Scanner in;
    private final PrintStream out;

    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Returns true if the user input line should be ignored.
     * Input should be ignored if it is parsed as a comment, is only whitespace, or is empty.
     *
     * @param rawInputLine full raw user input line.
     * @return true if the entire user input line should be ignored.
     */
    private boolean shouldIgnore(String rawInputLine) {
        if (rawInputLine.trim().isEmpty() || isCommentLine(rawInputLine)) {
            showToUser("OOPS WRONG FORMAT");
            return true;
        }
        return false;
    }

    /**
     * Returns true if the user input line is a comment line.
     *
     * @param rawInputLine full raw user input line.
     * @return true if input line is a comment.
     */
    private boolean isCommentLine(String rawInputLine) {
        // If input does not contain any of the actions
        if (!(rawInputLine.contains("bye") || rawInputLine.contains("done") || rawInputLine.contains("todo")
                || rawInputLine.contains("deadline") || rawInputLine.contains("list")
                || rawInputLine.contains("event") || rawInputLine.contains("delete")
                || rawInputLine.contains("time") || rawInputLine.contains("help"))) {
            return true;
        }
        return false;
    }

    /**
     * Prompts for the command and reads the text entered by the user.
     * Ignores empty, pure whitespace, and comment lines.
     * Echos the command back to the user.
     * @return command (full line) entered by the user
     */
    public String getUserCommand() {
        String fullInputLine = in.nextLine();
        // silently consume all ignored lines
        while (shouldIgnore(fullInputLine)) {
            fullInputLine = in.nextLine();
        }
        return fullInputLine;
    }

    /**
     * Generates and prints the welcome message upon the start of the application.
     */
    public void showWelcomeMessage() {
        showToUser("Hello! I'm Best2103/TBot","What can I do for you?");
    }

    public void showGoodbyeMessage() {
        showToUser("Goodbye :(");
    }

    /** Shows message(s) to the user */
    public void showToUser(String... message) {
        out.println(INDENT + DIVIDER);
        for (String m : message) {
            if (m.length() > 0) {
                out.println(INDENT + m);
                out.println(INDENT + DIVIDER);
            }
        }
    }

    public void showInitFailedMessage() {
        System.out.println(INDENT + "OOPS SOMETHING WENT WRONG");
    }


    /**
     * Shows the result of a command execution to the user. Includes additional formatting to demarcate different
     * command execution segments.
     */
    public void showResultToUser(CommandResult result) {
        final Optional<List<Task>> resultTasks = result.getRelevantTasks();
        if (resultTasks.isPresent()) {
            showTaskListView(resultTasks.get());
        }
        showToUser(result.feedbackToUser);
    }

    /**
     * Shows a list of tasks to the user, formatted as an indexed list.
     * Private contact details are hidden.
     */
    private void showTaskListView(List<Task> tasks) {
        final List<String> formattedTasks = new ArrayList<>();
        for (Task task : tasks) {
            formattedTasks.add(task.toString());
        }
        showToUserAsIndexedList(formattedTasks);
    }

    private void showToUserAsIndexedList(List<String> list) {
        showToUser(getIndexedListForViewing(list));
    }

    /** Formats a list of strings as a viewable indexed list. */
    private static String getIndexedListForViewing(List<String> listItems) {
        final StringBuilder formatted = new StringBuilder();
        int displayIndex = 0 + DISPLAYED_INDEX_OFFSET;
        for (String listItem : listItems) {
            formatted.append(getIndexedListItem(displayIndex, listItem)).append("\n");
            displayIndex++;
        }
        return formatted.toString();
    }


    /**
     * Formats a string as a viewable indexed list item.
     *
     * @param visibleIndex visible index for this listing
     */
    private static String getIndexedListItem(int visibleIndex, String listItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
    }
}
