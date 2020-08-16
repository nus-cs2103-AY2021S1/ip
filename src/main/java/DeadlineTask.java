public class DeadlineTask extends Task {
    private String timeToBeDoneBy;

    public DeadlineTask(String taskName, String timeToBeDoneBy) {
        super(taskName);
        this.timeToBeDoneBy = timeToBeDoneBy;
    }

    @Override
    public String toString() {
        return "[D] [" + getStatusIcon() + "] " + taskName + " (by:" + timeToBeDoneBy + ")";
    }
}
