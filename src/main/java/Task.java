import java.time.format.DateTimeFormatter;

public abstract class Task {
    private static final String COMPLETE = "\u2713";
    private static final String NOT_COMPLETE = "\u2718";

    public static final String STORE_COMPLETED = COMPLETE;
    public static final String STORE_INCOMPLETE = NOT_COMPLETE;

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

    private void verifyArgs(String description) throws TaskException {
        if (description.equals("")) {
            throw new TaskException("Did you provide any description for this todo task?");
        }
    }

    public abstract TaskType getTaskType();

    public Task completeTask() {
        isComplete = true;
        return this;
    }

    protected String printCompletionFlag() {
        String completionFlag = isComplete ? COMPLETE : NOT_COMPLETE;
        return "[" + completionFlag + "]";
    }

    protected String storeCompletionFlag() {
        return isComplete ? STORE_COMPLETED : STORE_INCOMPLETE;
    }

    protected static boolean decodeCompletionFlag(String flag) {
        return flag.equals(STORE_COMPLETED);
    }

    public abstract String toStorageString();

    @Override
    public String toString() {
        return printCompletionFlag() + " | " + description;
    }
}
