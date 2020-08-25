package taskbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends TimedTask {
    /**
     * Creates an incomplete Event task
     * @param task Description of task
     * @param at Time when task occurs
     */
    public Event(String task, LocalDateTime at) {
        super(task, at);
    }

    /**
     * Creates an Event task given its completeness
     * @param task Description of task
     * @param at Time when task occurs
     * @param isDone Whether the task is complete
     */
    public Event(String task, LocalDateTime at, boolean isDone) {
        super(task, at, isDone);
    }

    /**
     * Gets the time the event occurs.
     * @return The time of the event.
     */
    public LocalDateTime getAt() {
        return getTime();
    }

    @Override
    public String toString() {
        return "[" + super.getStatusIcon() + "] " + "Event: " + super.getTask() +
                "(at: " + getTime().format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")) + ")" + "\n";
    }
}
