package duke.task;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime timeAt;

    /**
     * Constructs an Event object, which is a task with a time at which it is done.
     * @param desc The description of the event.
     * @param timeAt The time at which the event is done.
     * @throws ParseException when the date time formatting is wrong.
     */
    // TODO: 17/8/20 make a toString
    public Event(String desc, String timeAt) throws ParseException {
        super(desc);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        this.timeAt = LocalDateTime.parse(timeAt, format);
    }

    /**
     * Returns a string representation of the event.
     * The toString method for the Event object returns a string consisting of
     * [E] indicating event, the description of the event, and the time of the event.
     * @return A String representation of the Event object.
     */
    @Override
    public String toString() {
        String sign = isDone ? "✓" : "✗";
        return "[E][" + sign + "] " + description + " (at:" + timeAt + ")";
    }

    @Override
    public TaskType getType() {
        return TaskType.EVENT;
    }

    @Override
    public String getDate() {
        return timeAt.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    @Override
    public String getDelimiter() {
        return "/at";
    }
}
