import java.time.format.DateTimeFormatter;

public abstract class Task {
    private static final String COMPLETE = "\u2713";
    private static final String NOT_COMPLETE = "\u2718";

    public static final String STORE_COMPLETED = "COMP";
    public static final String STORE_INCOMPLETE = "ICOMP";

    protected static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM YY HH:mm");
    protected static final DateTimeFormatter READER_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    protected String description;
    protected boolean isComplete = false;

    protected Task(String description) {
        if (description.equals("")) {
            throw new TaskException("Did you provide any description for this todo task?");
        }
        this.description = description;
    }

    protected Task(String description, boolean isComplete) {
        if (description.equals("")) {
            throw new TaskException("Did you provide any description for this todo task?");
        }
        this.description = description;
        this.isComplete = isComplete;
    }

    public Task completeTask() {
        isComplete = true;
        return this;
    }

    public Task resetTask() {
        isComplete = false;
        return this;
    }

    protected String printCompletionFlag() {
        String completionFlag = isComplete ? COMPLETE : NOT_COMPLETE;
        return "[" + completionFlag + "]";
    }

    protected String getCompletionFlagStorage() {
        return isComplete ? STORE_COMPLETED : STORE_INCOMPLETE;
    }

    public abstract String toStorageString();

    @Override
    public String toString() {
        return printCompletionFlag() + " | " + description;
    }
}
