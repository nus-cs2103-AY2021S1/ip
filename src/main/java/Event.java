import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate date;
    public Event (String s, boolean isCompleted) {
        super(s.split("/at")[0].substring(5).strip(), isCompleted);
        date = LocalDate.parse(s.split("/at")[1].strip());
    }
    @Override
    public String toString() {
        String completion = this.isComplete() ? "[✓]" : "[✗]";
        return "[E]" + completion + " " + this.getTaskName() + " (at: "
            + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
    public String toFormattedString() {
        String completion = this.isComplete() ? "1" : "0";
        return "E | " + completion + " | " + this.getTaskName() + " | " + date;
    }
}
