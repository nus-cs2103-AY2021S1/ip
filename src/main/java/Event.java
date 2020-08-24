import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected static final String TASK_TYPE = "E";

    protected LocalDate dateBy;
    protected LocalTime timeBy;

    public Event(String description, String at) throws DukeException{
        super(description);
        try {
            String[] parts = at.split(" ", 2);
            this.dateBy = LocalDate.parse(parts[0]);
            this.timeBy = LocalTime.parse(parts[1]);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please input date and time in correct format: " +
                    "'yyyy-MM-dd HH:MM' (24-hour time format)");
        }
    }

    @Override
    public String getSaveFormat() {
        return String.format("%s | %s | %s %s",
                Event.TASK_TYPE,
                super.getSaveFormat(),
                this.dateBy.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                this.timeBy.format(DateTimeFormatter.ofPattern("HH:mm")));
    }

    @Override
    public String toString() {
        String modifier = (this.timeBy.isAfter(LocalTime.NOON)) ? "pm" : "am";
        return String.format("[%s]%s (by: %s, %s)",
                Deadline.TASK_TYPE,
                super.toString(),
                this.dateBy.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.timeBy.format(DateTimeFormatter.ofPattern("hh:mm")) + modifier);
    }
}