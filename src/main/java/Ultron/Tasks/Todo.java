package ultron.tasks;

public final class Todo extends Task {

    /**
     * Todo Task.
     * @param description   Description of the todo
     */
    //Call the constructor
    public Todo(final String description) {

        //Call the superclass constructor
        super(description);

    }

    /**
     * Parse the arguments required for todo class.
     * @param args  Arguments for the class
     * @return  Todo task
     */
    public static Todo parseCommand(final String args) {
        return new Todo(args);
    }

    /**
     * Get the type of command.
     * @return String TODO
     */
    @Override
    public String getType() {
        return "TODO";
    }

    /**
     * Get the Todo in the form of a command.
     * @return
     */
    @Override
    public String getCommand() {
        return String.format("%s", getMessage());
    }

    /**
     * Get the string representation of the todo.
     * @return  String command
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
