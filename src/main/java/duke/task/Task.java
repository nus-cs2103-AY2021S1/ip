package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a base representation of a Task.
 *
 * This base class provides a delimiter for storage.
 *
 * It also provides the display format for the datetime as dd MMM yy HH:mm (e.g. 01 Jan 20 15:00), and
 * the reading format of the datetime as dd/MM/yyyy HH:mm (e.g. 21/09/2020 19:00).
 */
public abstract class Task {
    private static final String COMPLETE = "\u2713";
    private static final String NOT_COMPLETE = "\u2718";

    private static final String STORE_COMPLETED = COMPLETE;
    private static final String STORE_INCOMPLETE = NOT_COMPLETE;

    protected static final String DELIMITER_STORAGE = " :: ";
    protected static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("dd MMM YY HH:mm");
    protected static final DateTimeFormatter READER_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    protected String description;
    protected boolean isComplete = false;

    protected Task(String description) throws TaskException {
        verifyArgs(description);
        this.description = description;
    }

    protected Task(String description, boolean isComplete) throws TaskException {
        verifyArgs(description);
        this.description = description;
        this.isComplete = isComplete;
    }

    /**
     * Checks if the description is empty.
     *
     * @param description Description of the task.
     * @throws TaskException If the description is empty.
     */
    private void verifyArgs(String description) throws TaskException {
        if (description.equals("")) {
            throw new TaskException("Did you provide any description for this todo task?");
        }
    }

    /**
     * Returns the type of the task.
     */
    public abstract TaskType getTaskType();

    /**
     * Indicates the task as completed.
     *
     * @return The calling instance.
     */
    public Task completeTask() {
        isComplete = true;
        return this;
    }

    /**
     * Returns the completion for display purposes.
     *
     * @return "[✘]" or "[✓]"
     */
    protected String printCompletionFlag() {
        String completionFlag = isComplete ? COMPLETE : NOT_COMPLETE;
        return "[" + completionFlag + "]";
    }

    /**
     * Returns the storage representation of the completion status.
     *
     * @return ✘ or ✓
     */
    protected String storeCompletionFlag() {
        return isComplete ? STORE_COMPLETED : STORE_INCOMPLETE;
    }

    /**
     * Converts the storage representation of the flag to completionStatus.
     *
     * @param flag Storage representation of a task.
     * @return True if the given flag is ✓
     */
    protected static boolean decodeCompletionFlag(String flag) {
        return flag.equals(STORE_COMPLETED);
    }

    /**
     * Returns the storage representation.
     *
     * @return The storage representation.
     */
    public abstract String toStorageString();

    /**
     * Checks if the task's description contains the searchTerm.
     *
     * @param searchTerm Term to search with.
     * @return True if the description has the given searchTerm.
     */
    public boolean descriptionContains(String searchTerm) {
        return description.contains(searchTerm);
    }

    /**
     * Returns the string representation of the current instance.
     *
     * @return String representation of the current instance.
     */
    @Override
    public String toString() {
        return printCompletionFlag() + " | " + description;
    }
}
