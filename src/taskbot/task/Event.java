package taskbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime at;

    /**
     * Creates an Event task
     * @param task Task to be completed
     * @param at Time when task occurs
     */
    public Event(String task, LocalDateTime at) {
        super(task);
        this.at = at;
    }

    /**
     * Gets the time of the event
     * @return LocalDateTime of event
     */
    public LocalDateTime getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[" + super.getStatusIcon() + "] " + "Event: " + super.getTask() +
                "(at: " + at.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")) + ")" + "\n";
    }
}
