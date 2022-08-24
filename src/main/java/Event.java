import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate date;
    public Event (String s) {
        super(s.split("/at")[0].substring(5).strip());
        date = LocalDate.parse(s.split("/at")[1].strip());
    }
    @Override
    public String toString() {
        String completion = this.isComplete() ? "[✓]" : "[✗]";
        return "[E]" + completion + " " + this.getTaskName() + " (at: "
            + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
