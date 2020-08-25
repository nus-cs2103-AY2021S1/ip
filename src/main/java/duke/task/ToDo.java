package duke.task;

/**
 * A Task with no defined datetime
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(boolean isCompleted, String description) {
        super(isCompleted, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Get the csv representation of this task
     * @return A csv String representative of this task
     */
    @Override
    public String toCsv() {
        return TaskFactory.TODO + "," + super.toCsv();
    }

}
