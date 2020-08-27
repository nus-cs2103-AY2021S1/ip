package alice.task;

/**
 * Represents a task in ALICE.
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Creates an undone task with the specified description.
     *
     * @param description describes the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a task with the indicated status and specified description.
     *
     * @param isDone the completion status of the task, true if completed; false otherwise.
     * @param description describes the task.
     */
    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        if (!isDone) {
            isDone = true;
        }
    }

    /**
     * Checks if the task description contains any of the provided keywords.
     *
     * @param keywords the list of keywords to check with.
     * @return true if any keywords matches the task description; false otherwise.
     */
    public boolean containKeywords(String[] keywords) {
        for (int i = 0; i < keywords.length; i++) {
            if (description.contains(keywords[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the task description.
     *
     * @return the description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the status completion of the task.
     *
     * @return O if the task is completed; X otherwise.
     */
    private String getStatusIcon() {
        return isDone ? "O" : "X";
    }

    /**
     * Encodes the task into a string for saving.
     * The task is encoded in a form that ALICE can understand.
     *
     * @return the string representation of the encoded task.
     */
    public String encode() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
