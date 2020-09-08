/**
 * <p>The EventTask class defines the behavior of an event task that has a
 * specific date and time when the event happens.</p>
 */
public class EventTask extends Task {
    private DateAndTime eventTime;

    public EventTask(String taskName, boolean isDone, DateAndTime eventTime, TagList tagList) {
        super(taskName, isDone, tagList);
        this.eventTime = eventTime;
    }

    @Override
    public String serialiseTask() {
        int isDone = getTaskStatus() ? 1 : 0;
        return "event %% " + getTaskDescription() + " %% " + isDone + " %% "
                + getEventTime().getDate() + " %% "
                + getEventTime().getTime() + " %% "
                + getTagList();
    }

    public DateAndTime getEventTime() {
        return eventTime;
    }

    @Override
    public String toString() {
        return "[E] [" + getStatusIcon() + "] " + taskDescription + " (at: " + eventTime + ")";
    }
}
