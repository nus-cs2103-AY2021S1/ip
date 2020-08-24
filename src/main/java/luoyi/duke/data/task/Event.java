package luoyi.duke.data.task;

import luoyi.duke.common.TimeWrapper;

/**
 * An Event class.
 * An Event is a task that start at a specific time and ends at a specific time.
 */
public class Event extends Task {
    private final TimeWrapper time;

    private Event(String description, TimeWrapper time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    /**
     * Returns a new uncompleted event.
     * @param description Description of event.
     * @param time Time during which the event is happening.
     * @return New uncompleted event.
     */
    public static Event getEvent(String description, String time) {
        return new Event(description, TimeWrapper.getTimeWrapper(time), false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task markComplete() {
        return new Event(description, time, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDataString() {
        return String.format("E|%d|%s|%s", isDone ? 1 : 0, description, time);
    }

    public boolean isSameTime(String date) {
        return time.equals(TimeWrapper.getTimeWrapper(date));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), time);
    }
}
