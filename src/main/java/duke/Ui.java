package duke;

/**
 * A user interface with standard messages used in interacting with the user.
 */
public class Ui {

    /** Standard messages as part of the UI's design. */
    private static final String MESSAGE_WELCOME = "Hello! I'm Duke, your list manager!\n"
            + "Before we get started, let me know if you would like to:\n"
            + "    i)  LOAD   <filetype> <filepath> : fetch a list you\n"
            + "        have made before, or\n"
            + "   ii)  CREATE <filetype> <filepath> : create a new list\n"
            + "        from scratch.";
    private static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    private static final String MESSAGE_TASK_ADDED = "Got it. I've added this task:";
    private static final String MESSAGE_TASK_DELETED = "Noted. I've removed this task:";
    private static final String MESSAGE_TASKS_CURRENT = "Now you have %d task(s) in the list.";
    private static final String MESSAGE_TASK_DONE = "Nice! I've marked this as done:";
    private static final String MESSAGE_LIST = "Here are the entries in your list:";
    private static final String MESSAGE_NO_ENTRIES = "There are no entries in your list.";
    private static final String MESSAGE_TASKS_FOUND = "Here are the matching tasks in your list:";
    private static final String MESSAGE_TASKS_NOT_FOUND = "Hmm...I did not manage to find any tasks containing ";
    private static final String MESSAGE_FILE_LOADED = "Got it. I have successfully loaded your list from ";
    private static final String MESSAGE_FILE_CREATED = "Congratulations! You now have a new list at ";
    private static final String MESSAGE_CATEGORY_ADDED = "Got it. I've added this category:";
    private static final String MESSAGE_CATEGORY_DELETED = "Noted. I've removed this category:";
    private static final String MESSAGE_AMOUNT_CHANGED = "Got it. I've updated your finance record:";
    private static final String MESSAGE_CATEGORY_RENAMED = "Done. I've renamed this category:";
    private static final String MESSAGE_FILE_UNLOADED = "Okay! I've unloaded the current file.";
    private static final String MESSAGE_TAG = "What can I do for you next?";
    private static final String ERROR_PREFIX = "\u2639 OOPS!!! ";
    private static final String CHANGE_FORMAT = "%-15s:    %.02f -> %.02f";

    /** The message to be passed to the user. */
    private String outputMessage;
    /** Constructs a new user interface object that interacts with the user input. */
    public Ui() {}

    /**
     * Gets the message to be passed to the user.
     *
     * @return The current message.
     */
    public String getMessage() {
        return outputMessage;
    }

    /**
     * Concatenates a sequence of lines, appending a linebreak after each line.
     *
     * @param lines The lines to be concatenated.
     * @return A string of the concatenated lines.
     */
    private static String concatenateLines(String... lines) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean isFirst = true;
        for (String s : lines) {
            if (!isFirst) {
                stringBuilder.append('\n');
            } else {
                isFirst = false;
            }
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    /**
     * Concatenates a sequence of lines, numbering them in increasing sequence.
     *
     * <p>Includes options to indicate whether there is a header (or a footer) to
     * skip when numbering them.</p>
     *
     * @param hasHeader The boolean indicator whether to skip the first line in numbering.
     * @param hasFooter The boolean indicator whether to skip the last line in numbering.
     * @param lines The lines to be concatenated.
     * @return A string of the numbered and concatenated lines.
     */
    private static String numberAndConcatenateLines(boolean hasHeader, boolean hasFooter, String... lines) {
        int start = 0;
        int end = lines.length;
        int number = 1;
        StringBuilder stringBuilder = new StringBuilder();

        if (hasHeader) {
            assert(lines.length > 0);
            stringBuilder.append(lines[0]);
            stringBuilder.append('\n');
            start++;
        }

        if (hasFooter) {
            assert end - start > 0;
            end--;
        }

        for (int i = start; i < end; i++) {
            stringBuilder.append(String.format("%d. %s\n", number++, lines[i]));
        }

        if (hasFooter) {
            stringBuilder.append(lines[lines.length - 1]);
        }
        return stringBuilder.toString();
    }

    /**
     * Gets the standard welcome message.
     * @return The standard welcome message.
     */
    public static String getWelcomeMessage() {
        return MESSAGE_WELCOME;
    }

    /**
     * Sets the output message to be the standard goodbye message.
     */
    public void goodbye() {
        outputMessage = MESSAGE_GOODBYE;
    }

    /**
     * Prepends a standard error prefix to a specified error message.
     *
     * @param errorMessage The main error message.
     * @return The error message with the standard error prefix.
     */
    public String addErrorPrefix(String errorMessage) {
        return ERROR_PREFIX + errorMessage;
    }

    /**
     * Concatenates the entries as a numbered list starting from 1, and sets it as the output message.
     *
     * @param entries The entries to be numbered and listed.
     */
    public void listNumberedEntries(String... entries) {
        assert entries.length > 0;
        String[] lines = new String[entries.length + 1];
        lines[0] = MESSAGE_LIST;
        System.arraycopy(entries, 0, lines, 1, entries.length);
        outputMessage = numberAndConcatenateLines(true, false, lines);
    }

    /**
     * Sets the output message to be the standard message for when the task-list is empty.
     */
    public void setNoEntriesMessage() {
        outputMessage = MESSAGE_NO_ENTRIES;
    }

    /**
     * Concatenates the tasks found matching the query as a numbered list starting from 1, and sets it as the
     * output message.
     *
     * @param finds The tasks found matching the query.
     */
    public void listNumberedFoundTasks(String... finds) {
        assert finds.length > 0;
        String[] lines = new String[finds.length + 1];
        lines[0] = MESSAGE_TASKS_FOUND;
        System.arraycopy(finds, 0, lines, 1, finds.length);
        outputMessage = numberAndConcatenateLines(true, false, lines);
    }

    /**
     * Sets the output message to inform the user that the search query returned no matching tasks.
     *
     * @param searchQuery The keyword used for searching the tasks.
     */
    public void setNoFoundTasksMessage(String searchQuery) {
        outputMessage = MESSAGE_TASKS_NOT_FOUND + searchQuery;
    }

    /**
     * Sets the output message to show that the file at the specified filepath was successfully loaded.
     *
     * @param filepath The specified filepath where the file was loaded from.
     */
    public void showLoadingSuccess(String filepath) {
        outputMessage = concatenateLines(MESSAGE_FILE_LOADED, filepath, MESSAGE_TAG);
    }

    /**
     * Sets the output message to show that the file was successfully created at the specified filepath.
     *
     * @param filepath The specified filepath where the file was created.
     */
    public void showMakeFileSuccess(String filepath) {
        outputMessage = concatenateLines(MESSAGE_FILE_CREATED, filepath, MESSAGE_TAG);
    }

    /**
     * Sets the output message to update the user that the task was successfully added to the task-list.
     *
     * @param task The task that was added.
     * @param numTasks The current number of tasks in the task-list.
     */
    public void showAddedTask(String task, int numTasks) {
        outputMessage = concatenateLines(
                MESSAGE_TASK_ADDED,
                " " + task,
                String.format(MESSAGE_TASKS_CURRENT, numTasks));
    }

    /**
     * Sets the output message to show that the task was successfully marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showDoneTask(String task) {
        outputMessage = concatenateLines(MESSAGE_TASK_DONE, "  " + task);
    }

    /**
     * Sets the output message to show that the task was successfully deleted from the task-list.
     *
     * @param task The task that was deleted.
     * @param numTasks The current number of tasks in the task-list.
     */
    public void showDeletedTask(String task, int numTasks) {
        outputMessage = concatenateLines(MESSAGE_TASK_DELETED,
                "  " + task,
                String.format(MESSAGE_TASKS_CURRENT, numTasks));

    }

    /**
     * Sets the output message to show that the specified category has been added.
     *
     * @param name The name of the added category.
     */
    public void showAddedCategory(String name) {
        outputMessage = concatenateLines(MESSAGE_CATEGORY_ADDED, name);
    }

    /**
     * Sets the output message to show that the specified category has been removed.
     *
     * @param name The name of the removed category.
     */
    public void showRemovedCategory(String name) {
        outputMessage = concatenateLines(MESSAGE_CATEGORY_DELETED, name);
    }

    /**
     * Sets the output message to show the change in amount within the category.
     *
     * @param name The name of the category.
     * @param previousAmount The previous amount within the category.
     * @param newAmount The new amount within the category.
     */
    public void showChangedAmount(String name, double previousAmount, double newAmount) {
        outputMessage = concatenateLines(MESSAGE_AMOUNT_CHANGED,
                String.format(CHANGE_FORMAT, name, previousAmount, newAmount));
    }

    /**
     * Sets the output message to show the change in name of the category.
     *
     * @param oldName The previous name of the category.
     * @param newName The new name of the category.
     */
    public void showRenamedCategory(String oldName, String newName) {
        outputMessage = concatenateLines(MESSAGE_CATEGORY_RENAMED, String.format("%s -> %s", oldName, newName));
    }

    /**
     * Sets the output message to show that the current file has been unloaded.
     */
    public void showUnloaded() {
        outputMessage = concatenateLines(MESSAGE_FILE_UNLOADED, MESSAGE_TAG);
    }
}
