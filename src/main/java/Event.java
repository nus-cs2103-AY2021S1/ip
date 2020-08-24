import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    public static String icon = "E";
    private String preposition;
    private LocalDateTime dateTime;

    Event(String taskString, String preposition, String dateTimeString) {
        super(taskString);
        this.preposition = preposition;
        this.dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    Event(String taskString, String preposition, String eventString, boolean status) {
        super(taskString);
        this.preposition = preposition;
        this.eventString = eventString;
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
        return "event//" + taskString + "//" + preposition + "//" + eventString + "//" + status;
    }
}
