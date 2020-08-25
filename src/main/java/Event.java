import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.time.format.DateTimeParseException;


public class Event extends Task {
    protected LocalDate date;

    public Event(String name, boolean isCompleted, String date) throws DateTimeParseException {
        super(name, isCompleted);
        this.date = LocalDate.parse(date);
    }

    public static Event newEvent(String name, String date){
        return new Event(name, false, date);
    }

    public static Event existingEvent(String name, boolean isCompleted, String date){
        return new Event(name, isCompleted, date);
    }

    public boolean isToday(){
        return this.date.isEqual(LocalDate.now());
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", " +
                date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()) + ")";
    }

    public String toSaveString(){
        return "E" + " | " + super.toSaveString() + " | " + this.date + "\n";
    }
}
