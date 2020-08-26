package duke.tasks;

import duke.exceptions.DukeException;

/**
 * Represents a Todo
 */
public class Todo extends Task {

    /**
     * Class constructor specifying whether the todo is complete.
     *
     * @param content    the contents of the todo
     * @param isComplete the completion status of the todo
     * @throws DukeException If an exception related to Duke occurred.
     */
    public Todo(String content, Boolean isComplete) throws DukeException {
        super(content, isComplete);
    }

    /**
     * Class constructor.
     *
     * @param content the contents of the todo
     * @throws DukeException If an exception related to Duke occurred.
     */
    public Todo(String content) throws DukeException {
        super(content);
    }

    /**
     * Returns a user-readable todo string.
     *
     * @return user-readable todo string
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Returns a todo string readable by storage.
     *
     * @return storage-safe todo string
     */
    @Override
    public String toSaveString() {
        return String.format("T/%s/ ", super.toSaveString());
    }
}
