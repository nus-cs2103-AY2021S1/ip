import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate byDate;
    protected LocalTime byTime;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.byDate = formatDate(by);
            this.byTime = formatTime(by);

        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            throw new DukeException("Please input date and time in correct format: YYYY/MM/DD HH:MM");
        }
    }

    public Deadline(String description, boolean isDone, String by) throws DukeException {
        super(description, isDone);
        try {
            this.byDate = formatDate(by);
            this.byTime = formatTime(by);

        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            throw new DukeException("Please input date and time in correct format: YYYY/MM/DD HH:MM");
        }
    }

    private LocalTime formatTime(String by) {
        String timePortion = by.substring(by.indexOf(" ") + 1);
        LocalTime time = LocalTime.parse(timePortion);
        return time;
    }

    private LocalDate formatDate(String by) {
        String datePortion = by.substring(0, by.indexOf(" ")).replaceAll("/", "-");
        LocalDate date = LocalDate.parse(datePortion);
        return date;
    }

    @Override
    public String toString(){
        return "D | " + super.toString() + " | " + this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                ", " + this.byTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }
}