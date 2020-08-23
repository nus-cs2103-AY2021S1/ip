package duke.task;

/**
 * Represent a Todo object
 * A <code>Todo</code> corresponds to a task created using the command
 * "todo"
 */
public class Todo extends Task {

    /**
     * Constructor of the Todo Class
     *
     * @param description description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor of the Todo Class
     *
     * @param description description of the task
     * @param status status of the task
     */
    public Todo(String description, int status) {
        super(description, status);
    }

    /**
     * Returns a string to be written inside the text file
     *
     * @param status current status of the task
     * @return string representation of the task
     */
    public String saveText(int status) {
        return "T | " + status + " | " + description;
    }

    /**
     * Returns a string representation of a task object
     *
     * @return string representation of a task object
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
