package duke.task;

/**
 * Represents a mapping of a taskNumber to a task
 */
public class NumberedTask {

    private int number;
    private Task task;

    public NumberedTask(int number, Task task) {
        this.number = number;
        this.task = task;
    }

    @Override
    public String toString() {
        return String.format("%d.%s", number, task);
    }
}
