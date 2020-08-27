package duke.tasks;

/**
 * Class to define a Todo.
 */
public class Todo extends Task {

    /**
     * Creates a Todo with the given task name.
     *
     * @param task Task name
     */
    public Todo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns string representation of the Todo to store in file.
     *
     * @return string representation
     */
    @Override
    public String fileString() {
        return "T|" + super.fileString();
    }
}
