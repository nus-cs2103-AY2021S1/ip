package duke.task;

import duke.exception.MissingDeadlineException;

/**
 * Represents a task that is occurring at a specific time.
 */
public class Event extends Task {
    protected String eventTime;

    public Event(String desc) throws MissingDeadlineException {
        super("E", desc.split(" /at ", 2)[0]);
        String[] temp = desc.split(" /at ", 2);
        if (temp.length == 1) {
            throw new MissingDeadlineException("event");
        } else {
            String date = desc.split(" /at ", 2)[1];
            eventTime = containsTime(date)
                            ? formatDateTime(date)
                            : formatDate(date);
        }
    }

    public Event(String desc, String eventTime) {
        super("E", desc);
        this.eventTime = eventTime;
    }

    @Override
    public String formatTaskForFile() {
        return taskType + " | " + (isDone ? "1" : "0") + " | " +
                description + " | " + eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + description + " (at: " + eventTime + ")";
    }
}
