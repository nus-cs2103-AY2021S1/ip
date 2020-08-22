package main.java.duke.task;

public class ToDo extends Task{

    /**
     * Creates a corresponding task with the description of the task.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a representation of the task in terms of the description and state.
     *
     * @return The representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a representation of the task in terms of the description and
     * state for the task to be stored into the record.
     *
     * @return The representation of the task in terms of
     *         the description, the state of completion, and time.
     */
    @Override
    public String record() {
        if(this.isCompleted) {
            return "T | 1 | " + description;
        } else {
            return "T | 0 | " + description;
        }
    }
}
