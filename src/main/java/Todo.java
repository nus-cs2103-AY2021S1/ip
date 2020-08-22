/**
 * Encapsulates a Todo task.
 */
public class Todo extends Task {

    /**
     * Creates a todo task.
     *
     * @param name Name of task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Parses a split serialized string from saved data.
     *
     * @param split Serialized string split by the "|" delimiter.
     * @return Todo task, or null if save file is corrupted.
     */
    public static Todo parse(String[] split) {
        Todo todo = new Todo(split[2]);
        if (split[1].equals("1")) {
            todo.markDone();
        }
        return todo;
    }

    /**
     * Serializes the task into a string to be saved.
     *
     * @return Serialized string.
     */
    public String serialize() {
        return "T|" + super.serialize();
    }

    /**
     * Returns the string representation of the todo task.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
