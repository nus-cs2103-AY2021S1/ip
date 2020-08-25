package duke.task;

public class ComplexTask extends Task {

    private String time;
    private TaskType taskType;

    public ComplexTask(String description, String time, TaskType taskType) {
        super(description, false, taskType, time);
        this.time = time;
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        if (taskType == TaskType.EVENT) {
            return "[E]" + super.toString() + " (at: " + time + ")";
        } else { // DEADLINE
            return "[D]" + super.toString() + " (by: " + time + ")";
        }
    }
}
