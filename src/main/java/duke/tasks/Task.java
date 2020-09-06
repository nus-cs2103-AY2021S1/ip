package duke.tasks;

import duke.exceptions.DukeException;

/**
 * Represents a task.
 */
public class Task {

    protected String content;
    protected boolean isComplete;
    protected Priority priority;

    /**
     * Initializes an incomplete task.
     *
     * @param content Contents of the task.
     * @param priority Priority of the task.
     * @throws DukeException If an exception related to Duke occurred.
     */
    public Task(String content, String priority) throws DukeException {
        if (content.replace(" ", "").equals("")) {
            throw new DukeException("Contents of a task cannot be empty.");
        }
        try {
            int priorityValue = Integer.parseInt(priority);
            this.priority = Priority.parseIntValue(priorityValue);
        } catch (NumberFormatException exception) {
            throw new DukeException("Priority specified is invalid, needs to be an integer.");
        }
        this.content = content;
        this.isComplete = false;
    }

    /**
     * Initializes a task specifying whether the task is complete.
     *
     * @param content Contents of the task.
     * @param priority Priority of the task.
     * @throws DukeException If an exception related to Duke occurred.
     */
    public Task(String content, boolean isComplete, String priority) throws DukeException {
        int priorityValue = Integer.parseInt(priority);
        this.priority = Priority.parseIntValue(priorityValue);
        this.content = content;
        this.isComplete = isComplete;
    }

    private static int booleanToInt(boolean bool) {
        return bool ? 1 : 0;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isComplete Completion status of the task.
     */
    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    /**
     * Returns a user-readable task string.
     *
     * @return User-readable task string.
     */
    @Override
    public String toString() {
        if (isComplete) {
            return String.format("[Y](%s) %s", this.priority, content);
        } else {
            return String.format("[X](%s) %s", this.priority, content);
        }
    }

    /**
     * Returns a task string readable by storage.
     *
     * @return Storage-safe task string.
     */
    public String toSaveString() {
        return String.format("%s/%s/%s", this.priority.toSaveString(), booleanToInt(isComplete), content);
    }

    public String getContent() {
        return content;
    }
}
