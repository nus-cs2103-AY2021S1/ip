package duke.task;

import duke.time.TimePoint;

import java.util.HashMap;

/**
 * Class that represents an event that occurs at some time.
 */
public class Event extends Task {

    /**
     * Represents time at which event occurs.
     */
    private TimePoint when;

    /**
     * Creates an initialised <code>Event</code> with name and time.
     *
     * @param name Name of <code>Event</code>.
     * @param when Time when <code>Event</code> occurs.
     */
    public Event(String name, String when) {
        super(name);
        this.when = TimePoint.of(when);
    }

    /**
     * Creates a fully detailed <code>Event</code>.
     * For creating <code>Event</code> from save file.
     *
     * @param name Name of <code>Event</code>.
     * @param when Time when <code>Event</code> occurs.
     * @param done Whether <code>Event</code> has been done.
     */
    public Event(String name, String when, boolean done) {
        super(name, done);
        this.when = TimePoint.of(when);
    }

    /**
     * Returns String formatted for representation of <code>Event</code> for display.
     *
     * @return Formatted String.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.when.toString() + ")";
    }

    /**
     * Converts <code>Event</code> to <code>HashMap</code> representation.
     * Used for further processing to save file string.
     *
     * @return HashMap representation of properties.
     */
    @Override
    public HashMap<String, String> convertToHashMap() {
        HashMap<String, String> dict = super.convertToHashMap();
        dict.put("type", "duke.task.Event");
        dict.put("when", this.when.toSaveString());
        return dict;
    }
}
