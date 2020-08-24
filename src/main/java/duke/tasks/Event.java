package duke.tasks;

import duke.utils.DukeDateTime;

/** Represents an event. */
public class Event extends Task {

    /** The date and/or time of the event. */
    protected DukeDateTime at;

    /** Constructs an Event object with the specified description and date and/or time.
     *
     * @param description The description of this event.
     * @param at The date and/or time of this event.
     */
    public Event(String description, DukeDateTime at) {
        super(description);
        this.at = at;
    }

    /** Returns the date and/or time of this event.
     *
     * @return The date and/or time of this event.
     */
    public DukeDateTime getAt() {
        return at;
    }

    /** Returns the String representation of this event in the format that it should be saved in the file.
     *
     * @return The String representation of this event in the appropriate format.
     */
    @Override
    public String toFileString() {
        return String.format("E | %d | %s | %s", isDone ? 1 : 0, description, at);
    }

    /** Returns the String representation of this event to be displayed to the user.
     *
     * @return The String representation of this event to be displayed to the user.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.toString() + ")";
    }
}
