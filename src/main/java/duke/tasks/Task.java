package duke.tasks;

import duke.exceptions.DukeException;

/**
 * Represents a task.
 */
public class Task {

    public String content;
    public boolean isComplete;

    /**
     * Class constructor.
     * @param content the contents of the task
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
     * @param content the contents of the task
     * @throws DukeException If an exception related to Duke occurred.
     */
    public Task(String content, boolean isComplete) {
        this.content = content;
        this.isComplete = isComplete;
    }

    /**
     * Sets the completion status of the task.
     * @param isComplete the completion status of the task
     */
    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    /**
     * Returns a user-readable task string.
     * @return user-readable task string
     */
    @Override
    public String toString() {
        if (isComplete) {
            return String.format("[Y] %s", content);
        } else {
            return String.format("[X] %s", content);
        }
    }

    private static int booleanToInt(boolean bool) {
        return bool ? 1 : 0;
    }

    /**
     * Returns a task string readable by storage.
     * @return storage-safe task string
     */
    public String toSaveString() {
        return String.format("%s/%s", booleanToInt(isComplete), content);
    }
}
