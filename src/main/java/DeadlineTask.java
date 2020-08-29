/**
 * <p>The DeadlineTask class defines the behavior of deadline task that has a
 * specific date to be done by for the task.</p>
 */
public class DeadlineTask extends Task {
    private DateAndTime timeToBeDoneBy;

    /**
     * Creates a Task object that has a description and status of whether the task is done.
     * @param taskDescription A String that represents the description of the task
     * @param isDone A boolean that shows the status of the task
     * @param timeToBeDoneBy A DateAndTime object to represent the deadline
     */
    public DeadlineTask(String taskDescription, boolean isDone, DateAndTime timeToBeDoneBy) {
        super(taskDescription, isDone);
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
