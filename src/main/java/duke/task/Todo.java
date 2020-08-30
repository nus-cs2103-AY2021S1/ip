package duke.task;

/**
 * Represents a Todo and consists of methods related to Todo Task.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo Task.
     *
     * @param name Task name.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Returns a string of the format required by the storage file.
     *
     * @return String describing the todo duke.task.
     */
    @Override
    public String writeToFile() {
        return "todo" + "|" + this.getStatusSymbol() + "|"
                + this.taskName;

    }

    /**
     * Returns a string of a format to be printed by Duke.
     *
     * @return String describing the todo duke.task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
