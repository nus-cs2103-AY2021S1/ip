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
            // Convert string to date and time format
            String datePortion = by.substring(0, by.indexOf(" ")).replaceAll("/", "-");
            String timePortion = by.substring(by.indexOf(" ") + 1);
            LocalDate date = LocalDate.parse(datePortion);
            LocalTime time = LocalTime.parse(timePortion);
            this.byDate = date;
            this.byTime = time;

        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            throw new DukeException("Please input date and time in correct format: YYYY/MM/DD HH:MM");
        }
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                ", " + this.byTime.format(DateTimeFormatter.ISO_LOCAL_TIME) + ")";
    }
}