import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Deadline extends Task {
    private LocalDateTime dateTime;
    private String time;

    public Deadline(String s, LocalDateTime dateTime) {
        super(s);
        this.dateTime = dateTime;
        int minute = dateTime.getMinute();
        this.time = minute < 10
                ? String.format("%d:0$d", dateTime.getHour(), minute)
                : String.format("%d:$d", dateTime.getHour(), minute);
    }

    @Override
    public String toString() {
        String month = dateTime.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        int day = dateTime.getDayOfMonth();
        int year = dateTime.getYear();
        String dueDate = String.format("%s %d %d, %s", month, day, year, time);
        return done
                ? "[D][✓] " + text + " (by: " + dueDate + ")"
                : "[D][✗] " + text + " (by: " + dueDate + ")";
    }
}
