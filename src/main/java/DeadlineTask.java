/**
 * <p>The DeadlineTask class defines the behavior of deadline task that has a
 * specific date to be done by for the task.</p>
 */
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
