package duke.task;

/**
 * Represents a mapping of a taskNumber to a Task
 */
public class NumberedTask {

    private int taskNumber;
    private Task task;

    public NumberedTask(int taskNumber, Task task) {
        this.taskNumber = taskNumber;
        this.task = task;
    }

    @Override
    public String toString() {
        return String.format("%d.%s", taskNumber, task);
    }
}
