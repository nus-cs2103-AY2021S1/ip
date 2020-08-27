import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Deadline extends Task {
    private LocalDate dueDate;

    public Deadline(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public String toString() {
        String month = dueDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("en"));
        int day = dueDate.getDayOfMonth();
        int year = dueDate.getYear();
        String timeDisplay = String.format("%d %s %d", day, month, year);
        return "[D]" + super.toString() + String.format(" (by: %s)", timeDisplay);
    }
}
