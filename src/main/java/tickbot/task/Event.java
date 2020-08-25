package tickbot.task;

import java.time.LocalDate;

/**
 * The class to represent an event task.
 */
public class Event extends Task {
    public Event(boolean completed, String content, LocalDate time) {
        super(completed, content, time);
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String getTimeMarker() {
        return "at";
    }
}