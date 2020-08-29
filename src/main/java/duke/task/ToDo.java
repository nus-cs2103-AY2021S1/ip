package duke.task;

/**
 * Represent a to-do task for the duke program and extends task class without any additional field.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Return the data of this to-do task to be stored in the storage.
     * @return data of this to-do task as a String
     */
    @Override
    public String getData() {
        return "t/" + super.getData();
    }

    /**
     * Return the string representation of this to-do task.
     * @return string representation
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
