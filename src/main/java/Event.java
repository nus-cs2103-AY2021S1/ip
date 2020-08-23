import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDate atDate;
    protected LocalTime atTime;

    public Event (String description, String at) throws DukeException {
        super(description);

        try {
            // Convert string to date and time format
            String datePortion = at.substring(0, at.indexOf(" ")).replaceAll("/", "-");
            String timePortion = at.substring(at.indexOf(" ") + 1);
            LocalDate date = LocalDate.parse(datePortion);
            LocalTime time = LocalTime.parse(timePortion);

            this.atDate = date;
            this.atTime = time;
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            throw new DukeException("Please input date and time in correct format: YYYY/MM/DD HH:MM");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                ", " + this.atTime.format(DateTimeFormatter.ISO_LOCAL_TIME) + ")";
    }
}
