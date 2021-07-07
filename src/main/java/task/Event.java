package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime time;

    /**
     * Constructs an <code>Event</code> object.
     * @param description The description of the event
     * @param time The time of the event
     */
    public Event(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    /**
     * Constructs an <code>Event</code> object knowing whether the event is already done or not.
     * @param description The description of the event
     * @param time The time of the event
     * @param isDone Whether the event is already done or not
     */
    public Event(String description, LocalDateTime time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy, HH:mm");
        return super.toString() + " (at: " + time.format(formatter) + ")";
    }

    @Override
    public String getSimplifiedString() {
        return super.getSimplifiedString() + " - " + this.time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Event) {
            Event other = (Event) o;
            return this.description.equals(other.description) && this.time.equals(other.time);
        } else {
            return false;
        }
    }
}
