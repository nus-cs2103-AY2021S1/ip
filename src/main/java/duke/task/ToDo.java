package duke.task;

/**
 * Class representing a todo.
 */
public class ToDo extends Task {
    /**
     * Creates a brand new {@code ToDo}.
     * @param description Description of the todo.
     */
    public ToDo(String description) {
        super(description);
        this.taskType = "T";
    }

//    /**
//     * Creates a {@code ToDo} from existing data.
//     * @param uniqueId Unique Id of the todo.
//     * @param isDone Todo completion status.
//     * @param description Description of the todo.
//     */
//    public ToDo(String uniqueId, boolean isDone, String description) {
//        super(uniqueId, isDone, description);
//        taskType = "T";
//    }
    public ToDo(boolean isDone, String description) {
        this(description);
        if (isDone) {
            this.markDone();
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
