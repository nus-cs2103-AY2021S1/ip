package duke.tasks;

import java.time.LocalDate;

import duke.timeformatter.TimeFormatter;

/**
 * Represent a Task that has an associated date of occurrence.
 */
public class Event extends Task {

    /**
     * @param description Description of the Event.
     * @param at Date of the Event.
     */
    public Event(String description, LocalDate at) {
        super(description, at);
    }


    /**
     * Returns the String representation of this event to be displayed to the user.
     *
     * @return The String representation of this event to be displayed to the user.
     */
    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + super.toString() + " [at: "
                + TimeFormatter.prettyDate(localDate) + "]";
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.Event;
    }
}


