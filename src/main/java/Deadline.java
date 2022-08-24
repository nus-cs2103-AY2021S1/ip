import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    private LocalDate date;
    public Deadline (String s) {
        super(s.split("/by")[0].substring(8).strip());
        date = LocalDate.parse(s.split("/by")[1].strip());
    }
    @Override
    public String toString() {
        String completion = this.isComplete() ? "[✓]" : "[✗]";
        return "[D]" + completion + " " + this.getTaskName() + " (by: " +
                date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}