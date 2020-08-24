import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class Deadline extends Task {
    protected LocalDate date;

    public Deadline(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    public boolean isToday(){
        return this.date.isEqual(LocalDate.now());
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                ", " + date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()) + ")";
    }
}
