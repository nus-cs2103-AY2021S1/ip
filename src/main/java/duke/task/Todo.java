package duke.task;

import java.util.Optional;

/**
 * Represents Todo objects.
 * Inherits from the abstract <code>Task</code> class.
 */
public class Todo extends Task {
    public static final String TODO_SYMBOL = "T";

    /**
     * Constructor method.
     *
     * @param description the description of the <code>Todo</code>.
     * @param isCompleted the completion status of the <code>Todo</code>.
     */
    public Todo(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    /**
     * Creates a <code>Todo</code> that is not completed.
     *
     * @param description the description of the <code>Todo</code>.
     * @return an uncompleted <code>Todo</code>.
     */
    public static Todo createTodo(String description) {
        return new Todo(description, false);
    }

    /**
     * Converts the <code>Todo</code> to a <code>String</code>.
     *
     * @return a <code>String</code> representing the <code>Todo</code>.
     */
    @Override
    public String toString() {
        return "[" + TODO_SYMBOL + "]" + toStringSuffix();
    }

    /**
     * Checks if the other object is equivalent to a <code>Todo</code>.
     *
     * @param other the object to be compared to.
     * @return <code>true</code> if both objects are equal.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof Todo) {
            Todo otherTodo = (Todo) other;
            return this.isEqual(otherTodo);
        }
        return false;
    }

    /**
     * Retrieves the symbol of <code>Todo</code>.
     *
     * @return the <code>String</code> symbol of <code>Todo</code>.
     */
    @Override
    public String getTaskSymbol() {
        return TODO_SYMBOL;
    }

    /**
     * Gets the datetime of <code>Todo</code>.
     * Since <code>Todo</code> does not have a datetime attribute, it will be empty.
     *
     * @return an empty <code>Optional</code> object.
     */
    @Override
    public Optional<String> getTaskDatetime() {
        return Optional.empty();
    }
}
