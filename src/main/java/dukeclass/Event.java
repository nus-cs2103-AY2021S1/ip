package dukeclass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A type of task.
 * The task has a String message and a Boolean status.
 * in addition to that, it has a preposition and a timeline to be completed by
 */
public class Event extends Task {

    public static String icon = "E";
    private String preposition;
    private LocalDateTime dateTime;

    public Event(String taskString, String preposition, String dateTimeString) {
        super(taskString);
        this.preposition = preposition;
        this.dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public Event(String taskString, String preposition, String dateTimeString, boolean status) {
        super(taskString);
        this.preposition = preposition;
        this.dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.status = status;
    }

    @Override
    public String toString() {
        String statusIcon = (status)?"✓":"✗";
        return "[" + icon + "]" + "[" + statusIcon + "] "
                + this.taskString + " (" + this.preposition + ": "
                + dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }

    @Override
    public String toDataString() {
        return "event//" + taskString + "//" + preposition + "//"
                + dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + "//" + status;
    }
}
