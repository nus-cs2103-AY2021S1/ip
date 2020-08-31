package duke.task;

import duke.storage.CsvToTask;

/**
 * A Task with no defined datetime
 */
public class ToDo extends Task {

    /**
     * Create an undone To-Do task with the specified description
     * @param description A description of this task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Create an undone To-Do task with the specified description, and
     * additionally specifying the completion status of the To-Do
     * @param isCompleted A boolean to indicate the completion status of the task
     * @param description A description of this task
     */
    public ToDo(boolean isCompleted, String description) {
        super(isCompleted, description);
    }

    /**
     * @return A csv representation of this task.
     */
    @Override
    public String toCsv() {
        return CsvToTask.TODO + ","
                + this.isCompleted() + ","
                + this.getDescription();
    }

    /**
     * @return A readable text representation of this task.
     */
    @Override
    public String toString() {
        return "[T]"
                + "[" + this.isCompletedSymbol() + "]"
                + " " + this.getDescription();
    }

}
