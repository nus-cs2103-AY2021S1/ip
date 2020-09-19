package task;


/**
 * The TodoTask class inherits method from the Task class.
 * It would create EventTask object with the given description.
 */
public class TodoTask extends Task {
    private static final String TODO = "[T]";

    /**
     * Constructs a new TodoTask with empty description.
     */
    public TodoTask() {
        super("");
    }

    /**
     * Costructs a new TodoTask with the given task description.
     *
     * @param description todo task description.
     */
    public TodoTask(String description) {
        super(description);
    }


    /**
     * Returns messages to user. The result is the concatenation of :
     * 1. The type of the Task.
     * 2. The status of the task.
     * 3. The description of the task.
     *
     * @return String Returns the task type ([T]]), its status as well
     * as its description.
     */
    @Override
    public String toString() {
        return TODO + super.toString();
    }
}
