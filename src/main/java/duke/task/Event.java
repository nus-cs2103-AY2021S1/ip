package duke.task;

import java.util.Optional;

/**
 * This is an Event Task.
 * Keeps a description as well as a time at which the event occurs.
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructs a new Event Task.
     * @param description Description of Task
     * @param at Time at which the Event occurs.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getStringType() {
        return "E";
    }

    @Override
    public Optional<String> getDate() {
        return Optional.of(this.at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
