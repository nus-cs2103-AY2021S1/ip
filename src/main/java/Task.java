/**
 * The Task class is the abstract class for deadline, event and todos.
 * It has the task name, and can be marked as done.
 */
public abstract class Task {
    protected boolean isCompleted = false;
    protected String name;
    private static final String CHECKMARK = "\u2713";
    private static final String CROSS = "\u2718";
    private static final String SAVE_COMPLETED_STATUS = "1";
    private static final String SAVE_NOT_COMPLETED_STATUS = "0";



    protected Task (String name, boolean isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
    }

    /**
     * Marks a task as done.
     */
    public void markDone() {
        isCompleted = true;
    }

    @Override
    public String toString() {
        String completionStatus = "[" + (isCompleted ? CHECKMARK : CROSS) + "]";
        return completionStatus + " " + name;
    }

    /**
     * Converts a todo to a string format to be written into a file
     * @return string format to be written into a file
     */
    public String toSaveFormat() {
        String completionStatus = isCompleted ? SAVE_COMPLETED_STATUS : SAVE_NOT_COMPLETED_STATUS;
        return completionStatus + " | " + name;
    }
}
