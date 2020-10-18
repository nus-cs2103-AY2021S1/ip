package duke.task;

/**
 * Represents a ToDo task.
 */
public class ToDo extends Task {

    /**
     * Creates a {@code ToDo} with given content.
     * @param taskContent A String of task content.
     */
    public ToDo(String taskContent) {
        super(taskContent);
    }

    @Override
    public String getType() {
        return "T";
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

