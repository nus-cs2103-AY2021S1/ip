import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Event extends Task {
    private LocalDateTime dateTime;
    private String time;

    public Event(String s, LocalDateTime dateTime) {
        super(s);
        this.dateTime = dateTime;
        String minute = dateTime.getMinute() < 10
                ? "0" + dateTime.getMinute()
                : String.format("%d", dateTime.getMinute());
        String hour = dateTime.getHour() < 10
                ? "0" + dateTime.getHour()
                : String.format("%d", dateTime.getHour());
        this.time = String.format("%s:%s", hour, minute);
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

    public boolean compareTime(LocalDateTime now, long hours) {
        return now.plusHours(hours).isAfter(dateTime);
    }

    public String toCommand() {
        String rawDateTime = dateTime.toString();
        String pattern = "(\\d\\d\\d\\d-\\d\\d-\\d\\d)T(\\d\\d):(\\d\\d)";
        String date = rawDateTime.replaceAll(pattern, "$4");
        String hours = rawDateTime.replaceAll(pattern, "$5");
        String minutes = rawDateTime.replaceAll(pattern, "$6");
        String newDateTime = date + " " + hours + minutes;
        return done
                ? "done event " + text + " /at " + newDateTime
                : "event " + text + " /at " + newDateTime;
    }
}
