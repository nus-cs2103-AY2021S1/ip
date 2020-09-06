package duke.tasks;

/**
 * Todo is a type of Task.
 */

public class Todo extends Task {
    /**
     * Constructs a Todo object.
     * @param name Description of the todo
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Gets the type of the task.
     * @return The type of a Todo which is "T"
     */
    @Override
    public String getType() {
        return "T";
    }

    /**
     * The toString method of Todo.
     *
     * @return The required String format of a todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
