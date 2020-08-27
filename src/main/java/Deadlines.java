import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    LocalDate date;
    LocalTime time;

    Deadlines(String name, String time) {
        super(name,time);
        String[] by = time.split(" ");
        this.date = parseDate(by[1]);
        this.time = parseTime(by[2]);
    }

    public LocalDate parseDate(String dateString) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateString, format);
    }

    public LocalTime parseTime(String timeString) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HHmm");
        return LocalTime.parse(timeString, format);
    }

    public String printDateTime() {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("h:mma");
        return String.format("%s, %s",
                formatDate.format(date),
                formatTime.format(time));
    }

    @Override
    public String toString() {
        if (super.completed) {
            return "[D]" + "[" + "✓" + "] " + name + " (by: " + printDateTime() + ")";
        } else {
            return "[D]" + "[" + "✗" + "] " + name + " (by: " + printDateTime() + ")";
        }
    }
}
