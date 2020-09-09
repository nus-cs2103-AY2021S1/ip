package duke.tasks;

import java.time.LocalDate;

import duke.timeformatter.TimeFormatter;

/**
 * Represent a task that specifies a time of occurrence.
 */
public class Event extends Task {

    /**
     * @param description description of the event.
     * @param at          date of the event.
     */
    public Event(String description, LocalDate at) {
        super(description, at);
    }

    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + super.toString() + "(at: "
                + TimeFormatter.prettyDate(localDate) + ")";
    }

    @Override
    public String getTaskType() {
        return "Event";
    }
}


