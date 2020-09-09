package duke.task;

/**
 * Represents an Todo Task. It has a description attribute.
 */
public class Todo extends Task {

    private static final String TYPE = "T";
    private static final String TYPE_ICON = "[T]";

    /**
     * Creates a Todo task that has a description.
     *
     * @param description Describes the task to be done.
     */
    public Todo(String description) {
        super(description);
        this.storeAs = storeNotDoneTodo(description);

    }

    /**
     * Creates a Todo Task with a done indicator and a description.
     *
     * @param done        Indicates whether the task has been done.
     * @param description Describes the task.
     */
    public Todo(String done, String description) {
        super(description);

        if (done.equals(DONE)) {
            this.isDone = true;
            this.storeAs = storeDoneTodo(description);
        }
        this.storeAs = storeNotDoneTodo(description);
    }

    /**
     * String representation of the Todo Task
     *
     * @return representation of the Todo Task
     */
    @Override
    public String toString() {
        return TYPE_ICON + super.toString();
    }

    private String storeDoneTodo(String description) {
        return TYPE + SEPARATOR + DONE + SEPARATOR + description;
    }

    private String storeNotDoneTodo(String description) {
        return TYPE + SEPARATOR + NOT_DONE + SEPARATOR + description;
    }

}
