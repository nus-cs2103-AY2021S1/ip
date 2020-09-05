package duke.task;

/**
 * Represents a Todo object.
 */
public class Todo extends Task {
    /**
     * Creates a todo.
     * @param name A string representing the name of the todo.
     * @param isComplete A boolean value representing whether the todo has been completed.
     */
    protected Todo(String name, boolean isComplete) {
        super(name, isComplete);
    }

    /**
     * Creates a new Todo.
     * The completion status is false by default.
     * @param name A string representing the name of the new todo.
     * @return The new todo created.
     */
    public static Todo newTodo(String name) {
        return new Todo(name, false);
    }

    /**
     * Creates a Todo using information in storage.
     * The todo created is an already previously existing todo with its details recorded in storage.
     * Usually called when starting up the application, to populate the TaskList.
     * @param name A string representing the name of the existing todo.
     * @param isComplete A boolean value representing whether the todo has been completed.
     * @return The existing todo created.
     */
    public static Todo existingTodo(String name, boolean isComplete) {
        return new Todo(name, isComplete);
    }

    /**
     * Gets the string representation of the todo object for printing.
     * This contains the completion status and the todo name, as well as the type (Todo).
     * @return A string representation of the todo for printing.
     */
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Gets the string representation of the todo object to be saved in storage.
     * This contains the completion status and the todo name, as well as the type (Todo).
     * The different fields are separated by a "|" character.
     * Completion status is represented by a 1 or 0.
     * @return A string representation of the todo object to be saved in storage.
     */
    public String toSaveString() {
        return "T" + " | " + super.toSaveString() + "\n";
    }
}
