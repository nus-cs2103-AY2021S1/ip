import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;
    protected LocalTime start;
    protected LocalTime end;

    public Event (String description, String at, String timeRange) {
        super(description);
        this.at = LocalDate.parse(at);
        this.start = LocalTime.parse(timeRange.split("-")[0]);
        this.end = LocalTime.parse(timeRange.split("-")[1]);
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mma");
        return "[E]" + super.toString() + " (at: "
                + dateFormatter.format(at) + " "
                + timeFormatter.format(start) + "-"
                + timeFormatter.format(end) + ")";
    }

    @Override
    public String toStringFileFormat() {
        return "[E]" + super.toStringFileFormat() + " (at: "
                + at + " " + start + "-" + end + ")";
    }
}
