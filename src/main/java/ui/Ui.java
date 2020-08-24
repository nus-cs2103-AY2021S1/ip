package ui;

import commands.CommandResult;
import data.task.Task;
import utils.Messages;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

// UI of the application.
public class Ui {

    public static final int DISPLAYED_INDEX_OFFSET = 1;
    private static final String LS = System.lineSeparator();
    private static final String LINE_PREFIX = "| ";
    private static final String DIVIDER = "------------------------------------------------------------------------------";

    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    private boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty();
    }

    public String getUserCommand() {
        out.print(LINE_PREFIX + "Enter command or type 'help': ");
        String fullInputLine = in.nextLine();

        // silently consume all ignored lines
        while (shouldIgnore(fullInputLine)) {
            fullInputLine = in.nextLine();
        }

        showToUser("[commands.Command entered: " + fullInputLine + "]\n");
        return fullInputLine;
    }

    public void showToUser(String... message) {
        for (String m : message) {
            out.println(LINE_PREFIX + m.replace("\n", LS + LINE_PREFIX));
        }
    }

    public void showInitFailedMessage() {
        showToUser(Messages.MESSAGE_INIT_FAILED, DIVIDER, DIVIDER);
    }

    public void showWelcomeMessage() {
        showToUser(
                DIVIDER,
                Messages.MESSAGE_WELCOME,
                DIVIDER);
    }

    public void showGoodbyeMessage() {
        showToUser(Messages.MESSAGE_GOODBYE, DIVIDER);
    }

    public void showTaskListView(List<? extends Task> tasklist) {
        final List<String> formattedTasks = new ArrayList<>();
        for (Task task : tasklist) {
            formattedTasks.add(task.toString());
        }
        showToUsersAsIndexedList(formattedTasks);
    }

    public void showResultToUser(CommandResult result) {
        final Optional<List<? extends Task>> tasks = result.getRelevantTasks();
        if (tasks.isPresent()) {
            showTaskListView(tasks.get());
        }
        showToUser(result.feedbackToUser, DIVIDER);
    }

    private void showToUsersAsIndexedList(List<String> list) {
        showToUser(getIndexedListForViewing(list), DIVIDER);
    }

    private static String getIndexedListForViewing(List<String> listItems) {
        final StringBuilder formatted = new StringBuilder();
        int displayIndex = 0 + DISPLAYED_INDEX_OFFSET;
        for (String listItem : listItems) {
            formatted.append(getIndexedListItem(displayIndex, listItem)).append("\n");
            displayIndex++;
        }
        return formatted.toString();
    }
    private static String getIndexedListItem(int visibleIndex, String listItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
    }

}
