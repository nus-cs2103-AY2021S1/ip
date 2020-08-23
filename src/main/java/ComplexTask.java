public class ComplexTask extends Task{

    private final String time;
    private final TaskType taskType;

    protected ComplexTask(String description, String time, TaskType taskType) {
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
