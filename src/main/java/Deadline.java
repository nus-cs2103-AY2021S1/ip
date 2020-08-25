import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.time.format.DateTimeParseException;


public class Deadline extends Task {
    protected LocalDate date;

    protected Deadline(String name, boolean isCompleted, String date) throws DateTimeParseException {
        super(name, isCompleted);
        this.date = LocalDate.parse(date);
    }

    public static Deadline newDeadline(String name, String date){
        return new Deadline(name, false, date);
    }

    public static Deadline existingDeadline(String name, boolean isCompleted, String date){
        return new Deadline(name, isCompleted, date);
    }

    public boolean isToday(){
        return this.date.isEqual(LocalDate.now());
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                ", " + date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()) + ")";
    }

    public String toSaveString(){
        return "D" + " | " + super.toSaveString() + " | " + this.date + "\n";
    }
}
