import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Event extends Task {
    private LocalDateTime dateTime;
    private String time;

    public Event(String s, String date, String hours, String minutes) {
        super(s);
        this.dateTime = LocalDateTime.parse(date + "T" + hours + ":" + minutes);
        this.time = hours + ":" + minutes;
    }

    @Override
    public String toString() {
        String month = dateTime.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        int day = dateTime.getDayOfMonth();
        int year = dateTime.getYear();
        String dueDate = String.format("%s %d %d, %s", month, day, year, time);
        return done
                ? "[E][✓] " + text + " (at: " + dueDate + ")"
                : "[E][✗] " + text + " (at: " + dueDate + ")";
    }
}
