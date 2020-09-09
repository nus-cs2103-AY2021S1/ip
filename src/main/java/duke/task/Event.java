package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Event task that inherits from Task class, and has an additional condition, which is when it is held at.
 */
public class Event extends Task {
    private LocalDate at;

    /**
     * Creates a new Event object.
     *
     * @param description details about the Event.
     * @param at time/date the event is held at.
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at.trim());
    }

    /**
     * Creates a new Event object.
     *
     * @param description details about the Event.
     * @param isDone whether Event is done or not.
     * @param at time/date the event is held at.
     */
    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = LocalDate.parse(at.trim());
    }

    /**
     * Constructor for a new Event object.
     *
     * @param description details about the Event.
     * @param isDone whether Deadline is done or not.
     * @param tagDescription description of tag.
     * @param at time/date the Deadline is due by in yyyy-mm-dd format.
     */
    public Event(String description, boolean isDone, String at, String tagDescription) {
        super(description, isDone, tagDescription);
        this.at = LocalDate.parse(at.trim());
    }

    /**
     * Checks if task contains the keyword.
     *
     * @return whether the task contains that keyword.
     */
    public boolean hasKeyword(String keyword) {
        return description.contains(keyword) || at.format(DateTimeFormatter.ofPattern("d MMM yyyy")).contains(keyword);
    }

    /**
     * Overrides toString method of Task class.
     *
     * @return Custom description of the event.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }

    /**
     * Returns task description and its isDone status for saving.
     *
     * @return string containing its description and its status icon.
     */
    @Override
    public String infoString() {
        String infoIsDone = "0";
        if (isDone) {
            infoIsDone = "1";
        }
        return "E | " + infoIsDone + " | " + this.description + " | " + at + " | " + this.tagDescription;
    }
}
