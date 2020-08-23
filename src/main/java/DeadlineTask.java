public class DeadlineTask extends Task {
    private DateAndTime timeToBeDoneBy;

    public DeadlineTask(String taskName, DateAndTime timeToBeDoneBy) {
        super(taskName);
        this.timeToBeDoneBy = timeToBeDoneBy;
    }

    @Override
    public String toString() {
        return "[D] [" + getStatusIcon() + "] " + taskDescription + " (by: " + timeToBeDoneBy + ")";
    }
}
