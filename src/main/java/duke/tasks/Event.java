package duke.tasks;

import java.util.Objects;
import java.util.Optional;

/**
 * The class for event object.
 */
public class Event extends Task {

    private String duration;

    /**
     * Default constructor for a new event object.
     * @param task
     * @param duration
     */
    public Event(String task, String duration) {
        super(task);
        this.duration = duration;
    }

    /**
     * Constructor for an event with an existing event object.
     * @param status
     * @param task
     * @param duration
     */
    public Event(String status, String task, String duration) {
        super(task, Objects.equals(status, "1") ? true : false);
        this.duration = duration;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + duration + ")";
    }

    @Override
    public Optional<String> getTime() {
        return Optional.of(duration);
    }
}
