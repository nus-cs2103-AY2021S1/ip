package tasks;
import exceptions.InvalidDescriptionException;

/**
 * Represents a todo, consisting of a description.
 * Throws InvalidDescriptionException if the description provided is blank.
 */
public class Todo extends Task {

    /**
     * Creates a new todo object with a given description.
     * @param description provided for the todo
     * @throws InvalidDescriptionException when description is empty
     */
    public Todo(String description) throws InvalidDescriptionException {
        super(description);
        if (description.isBlank()) {
            throw new InvalidDescriptionException("Hey! "
                    + "Todo description shouldn't be blank.");
        }
    }

    /**
     * Creates a new todo given a description and done status.
     * @param description provided for the todo
     * @param isDone provided for the todo
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the string that represents the todo.
     *
     * @return the string consisting of the tag,
     * done status and description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string that represents the todo in a database.
     *
     * @return the string consisting of the tag,
     * done status and description
     */
    @Override
    public String databaseString() {
        return "T | " + super.databaseString();
    }
}
