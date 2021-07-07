package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import duke.util.DateTimeHandler;

/**
 * Represents an event task with a time and date period.
 */
public class Event extends Task {
    protected String at;
    protected LocalDate atDate;
    protected LocalDateTime atDateTime;

    /**
     * Initializes an event task containing the task description and time and date of the event.
     *
     * @param description Description of the event task.
     * @param at          Event time and/or date information.
     */
    public Event(String description, String at) {
        super(description);

        this.atDateTime = DateTimeHandler.tryParseDateTime(at);
        if (!DateTimeHandler.isDateTimeParsed(atDateTime)) {
            this.atDate = DateTimeHandler.tryParseDate(at);
        }
        this.at = at;
    }

    /**
     * Initializes an event task containing the task description, if the task is done and time and date of the event.
     * This is an overloaded constructor to allow for tasks in the hard drive to be loaded when Duke first runs.
     *
     * @param description Description of the event task.
     * @param isDone      If task is done.
     * @param at          Event time and/or date information.
     */
    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.atDateTime = DateTimeHandler.tryParseDateTime(at);
        if (!DateTimeHandler.isDateTimeParsed(atDateTime)) {
            this.atDate = DateTimeHandler.tryParseDate(at);
        }
        this.at = at;
    }


    /**
     * Returns the event time and/or date of the event.
     *
     * @return String representing the event time and/or date of the event.
     */
    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[" + TaskType.EVENT.getInitial() + "]"
                + super.toString()
                + " (at: " + DateTimeHandler.generateDateTimeFormat(at, atDate, atDateTime) + ")";
    }
}
