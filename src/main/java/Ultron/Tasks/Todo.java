package ultron.tasks;

public final class Todo extends Task {

    /**
     * Todo Task.
     *
     * @param description Description of the todo.
     */
    public Todo(final String description) {
        super(description);
    }

    /**
     * Parse the arguments required for todo class.
     *
     * @param args Arguments for the class.
     * @return Todo task.
     */
    public static Todo parseCommand(final String args) {
        return new Todo(args);
    }

    /**
     * Get the type of command.
     *
     * @return String TODO.
     */
    @Override
    public String getType() {
        return "TODO";
    }

    /**
     * Get the Todo in the form of a command.
     *
     * @return String command represented as a string.
     */
    @Override
    public String getCommand() {
        return String.format("%s", getMessage());
    }

    /**
     * Get the string representation of the todo.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
