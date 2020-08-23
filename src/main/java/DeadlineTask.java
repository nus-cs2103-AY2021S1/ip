public class DeadlineTask extends Task {
    private DateAndTime timeToBeDoneBy;

    public DeadlineTask(String taskName, boolean isDone, DateAndTime timeToBeDoneBy) {
        super(taskName, isDone);
        this.timeToBeDoneBy = timeToBeDoneBy;
    }

    public DateAndTime getTimeToBeDoneBy() {
        return timeToBeDoneBy;
    }

    @Override
    public String toString() {
        return "[D] [" + getStatusIcon() + "] " + taskDescription + " (by: " + timeToBeDoneBy + ")";
    }
}
