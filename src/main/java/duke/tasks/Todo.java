package duke.tasks;

import duke.exceptions.DukeException;

/**
 * Represents a Todo
 */
public class Todo extends Task {

    /**
     * Initializes a todo specifying whether the todo is complete.
     *
     * @param content Contents of the todo.
     * @param isComplete Completion status of the todo.
     * @param priority Priority of the todo.
     * @throws DukeException If an exception related to Duke occurred.
     */
    public Todo(String content, Boolean isComplete, String priority) throws DukeException {
        super(content, isComplete, priority);
    }

    /**
     * Initializes an incomplete todo.
     *
     * @param content Contents of the todo.
     * @param priority
     * @throws DukeException If an exception related to Duke occurred.
     */
    public Todo(String content, String priority) throws DukeException {
        super(content, priority);
    }

    /**
     * Returns a user-readable todo string.
     *
     * @return User-readable todo string.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Returns a todo string readable by storage.
     *
     * @return Storage-safe todo string.
     */
    @Override
    public String toSaveString() {
        return String.format("T/%s/ ", super.toSaveString());
    }
}
