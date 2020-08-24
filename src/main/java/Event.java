import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class Event extends Task {
    protected LocalDate date;

    public Event(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    public boolean isToday(){
        return this.date.isEqual(LocalDate.now());
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", " +
                date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()) + ")";
    }
}
