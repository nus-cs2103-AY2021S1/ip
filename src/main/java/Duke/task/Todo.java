package Duke.task;

/**
 * Represents an Todo Task. It has a description attribute.
 */
public class Todo extends Task {

    /**
     * Creates a Todo task that has a description.
     *
     * @param description Describes the task to be done.
     */
    public Todo(String description) {
        super(description);
        this.storeAs = "T,0," + description;
    }

    /**
     * Creates a Todo Task with a done indicator and a description.
     *
     * @param done Indicates whether the task has been done.
     * @param description Describes the task.
     */
    public Todo(String done, String description) {
        super(description);

        if (done.equals("1")) {
            this.isDone = true;
            this.storeAs = "T,1," + description;
        }
        this.storeAs = "T,0," + description;
    }

    /**
     * String representation of the Todo Task
     *
     * @return representation of the Todo Task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}