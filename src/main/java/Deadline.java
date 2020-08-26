import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task {

    protected LocalDate date;
    protected LocalTime time;

    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    public Deadline(String description, boolean isDone, LocalDate date, LocalTime time) {
        super(description, isDone);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toDisplayString() {
        return "[D]" + super.toDisplayString() + " (by: "
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " "
                + time.format(DateTimeFormatter.ofPattern("h:mm a")) + ")";
    }

    @Override
    public String toSavedString() {
        return "D" + " | " + super.toSavedString() + " | "
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " "
                + time.format(DateTimeFormatter.ofPattern("h:mm a"));
    }

}