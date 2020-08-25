import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {

    protected LocalDate time;

    public Event(String description, boolean isDone, LocalDate time) {
        super(description, isDone);
        this.time = time;
    }
    public String getTime(){
        return time.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getTime() + ")";
    }
}
