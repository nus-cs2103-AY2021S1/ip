package duke.tasks;

import duke.exceptions.DukeException;

/**
 * Represents a task.
 */
public class Task {

    protected String content;
    protected boolean isComplete;

    /**
     * Class constructor.
     *
     * @param content Contents of the task.
     * @throws DukeException If an exception related to Duke occurred.
     */
    public Task(String content) throws DukeException {
        if (content.replace(" ", "").equals("")) {
            throw new DukeException("Contents of a task cannot be empty.");
        }
        this.content = content;
        this.isComplete = false;
    }

    /**
     * Class constructor specifying whether the task is complete.
     *
     * @param content Contents of the task.
     * @throws DukeException If an exception related to Duke occurred.
     */
    public Task(String content, boolean isComplete) {
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
            return String.format("[Y] %s", content);
        } else {
            return String.format("[X] %s", content);
        }
    }

    /**
     * Returns a task string readable by storage.
     *
     * @return Storage-safe task string.
     */
    public String toSaveString() {
        return String.format("%s/%s", booleanToInt(isComplete), content);
    }

    public String getContent() {
        return content;
    }
}
