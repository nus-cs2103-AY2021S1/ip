public class DeadlineTask extends Task {
    private String deadline;
    private static final String SAVE_STRING = "DEADLINE|%s|%s|%s";

    public DeadlineTask(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    public DeadlineTask(boolean isDone, String taskName, String deadline) {
        super(isDone, taskName);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }

    @Override
    public String toSaveString() {
        return String.format(SAVE_STRING, super.isDone, super.taskName, this.deadline);
    }
}
