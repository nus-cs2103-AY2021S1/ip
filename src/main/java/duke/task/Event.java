package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Event extends Task {


    /**
     * Creates an events with given time.
     *
     * @param description Description of the event.
     * @param at Time for the event.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.time = Optional.of(at);
    }

    /**
     * Creates an events with status, description and time.
     *
     * @param isDone Status of the event.
     * @param description Description of the event.
     * @param at Time for the event.
     */
    public Event(boolean isDone, String description, LocalDateTime at) {
        super(description);
        this.time = Optional.of(at);
        this.isDone = isDone;
    }

    @Override
    public String toFileStringFormat() {
        assert time.isPresent();
        return String.format("E / %d / %s / %s", isDone ? 1 : 0, this.desciption, this.time.get());
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        assert time.isPresent();
        return "[E]" + super.toString() + " (at: " + time.get().format(formatter) + ")";
    }

}
