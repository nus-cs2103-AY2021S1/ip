package duke.tasks;

import java.util.Optional;

import duke.exceptions.NoSuchTentativeDateException;
import duke.utils.DukeDateTime;

/**
 * Represents an event.
 */
public class Event extends Task implements TimeBased {

    /** The date and/or time of the event. */
    protected Optional<DukeDateTime> at;

    /** The tentative schedule of the event. */
    protected TentativeSchedule tentativeSchedule;

    /**
     * Constructs an Event object with the specified description and DukeDateTime objects.
     *
     * @param description The description of this event.
     * @param timings The list of DukeDateTime objects of this event. If there are more than one
     *                timing given, they are put into the tentative schedule.
     */
    public Event(String description, DukeDateTime ... timings) {
        super(description);
        assert timings.length >= 1;
        if (timings.length == 1) {
            this.at = Optional.of(timings[0]);
            this.tentativeSchedule = new TentativeSchedule();
        } else {
            this.at = Optional.empty();
            this.tentativeSchedule = new TentativeSchedule(timings);
        }
    }

    private DukeDateTime getAt() {
        return at.orElse(null);
    }

    private String getTimeString() {
        return at.map(DukeDateTime::toString).orElse(tentativeSchedule.toString());
    }

    /**
     * Returns the String representation of this event in the format that it should be saved in the file.
     *
     * @return The String representation of this event in the appropriate format.
     */
    @Override
    public String toFileString() {
        return String.format("E | %d | %s | %s", isDone ? 1 : 0, description, getTimeString());
    }

    /**
     * Returns the String representation of this event to be displayed to the user.
     *
     * @return The String representation of this event to be displayed to the user.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getTimeString() + ")";
    }

    @Override
    public DukeDateTime getTime() {
        return getAt();
    }

    /**
     * Confirms a timing.
     *
     * @param index The index of the confirmed timing.
     * @return The modified event.
     * @throws NoSuchTentativeDateException If the index specified is out of bounds.
     */
    public Event confirm(int index) throws NoSuchTentativeDateException {
        DukeDateTime confirmedTiming = tentativeSchedule.getDateAtIndex(index);
        assert at.equals(Optional.empty());
        tentativeSchedule = new TentativeSchedule();
        at = Optional.of(confirmedTiming);
        return this;
    }
}
