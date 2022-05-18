package duke.task;

/**
 * Represents a assignment of a number to a Task.
 */
public class NumberedTask {

    private int taskNumber;
    private Task task;

    /**
     * Constructs a NumberTask with the given taskNumber and Task.
     *
     * The given Task is assigned the given number
     *
     * @param taskNumber number to be assigned to the Task
     * @param task Task to be assigned the number
     */
    public NumberedTask(int taskNumber, Task task) {
        assert(taskNumber > 0);
        this.taskNumber = taskNumber;
        this.task = task;
    }

    @Override
    public String toString() {
        return String.format("%d.%s", taskNumber, task);
    }
}
