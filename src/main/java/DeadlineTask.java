/**
 * <p>The DeadlineTask class defines the behavior of deadline task that has a
 * specific date to be done by for the task.</p>
 */
public class DeadlineTask extends Task {
    private DateAndTime deadlineTime;

    /**
     * Creates a Task object that has a description and status of whether the task is done.
     *
     * @param taskDescription A String that represents the description of the task.
     * @param isDone A boolean that shows the status of the task.
     * @param timeToBeDoneBy A DateAndTime object to represent the deadline.
     */
    public DeadlineTask(String taskDescription, boolean isDone, DateAndTime timeToBeDoneBy, TagList tagList) {
        super(taskDescription, isDone, tagList);
        this.deadlineTime = timeToBeDoneBy;
    }

    public DateAndTime getDeadlineTime() {
        return deadlineTime;
    }

    @Override
    public String serialiseTask() {
        int isDone = getTaskStatus() ? 1 : 0;
        return "deadline %% " + getTaskDescription() + " %% " + isDone + " %% "
                + getDeadlineTime().getDate().toString() + " %% " + getTagList();
    }

    @Override
    public String toString() {
        return "[D] [" + getStatusIcon() + "] " + taskDescription + " (by: " + deadlineTime + ")";
    }
}
