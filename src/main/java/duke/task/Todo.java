package duke.task;

import java.util.Optional;

/**
 * Represents Todo objects.
 * Inherits from the abstract Task class.
 */
public class Todo extends Task {
    public static final String TODO_SYMBOL = "T";

    /**
     * Constructor method.
     * @param description the description of the Todo.
     * @param isCompleted the completion status of the Todo.
     */
    public Todo(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    /**
     * Creates a Todo object that is not completed.
     * @param description the description of the Todo.
     * @return an uncompleted Todo object.
     */
    public static Todo createTodo(String description) {
        return new Todo(description, false);
    }

    /**
     * Converts the Todo object to a String,
     * @return a String representing the Todo object.
     */
    @Override
    public String toString() {
        return "[" + TODO_SYMBOL + "]" + toStringSuffix();
    }

    /**
     * Checks if the other Object is equivalent to the Todo object.
     * @param other the object to be compared to.
     * @return true if both objects are equal.
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
     * Retrieves the symbol of Todo.
     * @return the String symbol of Todo.
     */
    @Override
    public String getTaskSymbol() {
        return TODO_SYMBOL;
    }

    /**
     * Gets the Datetime of Todo.
     * Since Todo does not have a datetime attribute, it will be empty.
     * @return an empty Optional object.
     */
    @Override
    public Optional<String> getTaskDatetime() {
        return Optional.empty();
    }
}
