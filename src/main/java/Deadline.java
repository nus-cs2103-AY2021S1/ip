import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime datetime;
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy kk:mm");
    private static final DateTimeFormatter printFormatter = DateTimeFormatter.ofPattern("d MMM yyyy hh.mma");

    public Deadline(String description, String datetime) {
        super(description);
        this.datetime = LocalDateTime.parse(datetime, Deadline.inputFormatter);
    }

    private String datetimeString() {
        return "(by: " + this.datetime.format(Deadline.printFormatter) + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + this.datetimeString();
    }
}
