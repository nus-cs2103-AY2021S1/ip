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
            this.atDate = formatDate(at);
            this.atTime = formatTime(at);
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            throw new DukeException("Please input date and time in correct format: YYYY/MM/DD HH:MM");
        }
    }

    public Event(String description, boolean isDone, String at) throws DukeException {
        super(description, isDone);
        try {
            this.atDate = formatDate(at);
            this.atTime = formatTime(at);
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            throw new DukeException("Please input date and time in correct format: YYYY/MM/DD HH:MM");
        }
    }

    private LocalTime formatTime(String at) {
        String timePortion = at.substring(at.indexOf(" ") + 1);
        LocalTime time = LocalTime.parse(timePortion);
        return time;
    }

    private LocalDate formatDate(String at) {
        String datePortion = at.substring(0, at.indexOf(" ")).replaceAll("/", "-");
        LocalDate date = LocalDate.parse(datePortion);
        return date;
    }

    @Override
    public String toString() {
        return "E | " + super.toString() + " | " + this.atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                ", " + this.atTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }
}
