public class DeadlineTask extends Task {
    private String timeToBeDoneBy;

    public DeadlineTask(String taskName, boolean isDone, String timeToBeDoneBy) {
        super(taskName, isDone);
        this.timeToBeDoneBy = timeToBeDoneBy;
    }

    public String getTimeToBeDoneBy() {
        return timeToBeDoneBy;
    }

    @Override
    public String toString() {
        return "[D] [" + getStatusIcon() + "] " + taskDescription + " (by:" + timeToBeDoneBy + ")";
    }
}
