/**
 * <p>The EventTask class defines the behavior of an event task that has a
 * specific date and time when the event happens.</p>
 */
public class EventTask extends Task {
    private DateAndTime eventTime;

    /**
     * Creates an EventTask object with name, status, time, and tag.
     *
     * @param taskName A String representing task name.
     * @param isDone A boolean representing task status.
     * @param eventTime A DateAndTime event representing event time.
     * @param tagList A TagList to manage tags.
     */
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
