import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;
    protected LocalDate date;
    protected LocalTime time;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by;
        try {
            String[] dateTimeUnformattedArr = by.substring(1).split(" ");
            String dateUnformatted = dateTimeUnformattedArr[0];
            String timeUnformatted = dateTimeUnformattedArr[1];
            this.date = extractDate(dateUnformatted);
            this.time = extractTime(timeUnformatted);
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException | DateTimeException a) {
            throw new DukeException("Date / time not formatted correctly, please follow format: yyyy/mm/dd hh:mm");
        }
    }

    private LocalDate extractDate(String dateUnformatted) {
        dateUnformatted = dateUnformatted.replaceAll("/", "-");
        return LocalDate.parse(dateUnformatted);
    }

    private LocalTime extractTime(String timeUnformatted) {
        return LocalTime.parse(timeUnformatted);
    }

    @Override
    public String toString() {
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter dtfTime = DateTimeFormatter.ISO_LOCAL_TIME;
        return "[D]" + super.toString() + "(by: " + this.date.format(dtfDate) + " " + this.time.format(dtfTime) + ")";
    }
    @Override
    public String stringToSave() {
        char status = this.isDone ? '1' : '0';
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter dtfTime = DateTimeFormatter.ISO_LOCAL_TIME;
        return "D " + "| " + status + " | " + this.description + "| " + this.date.format(dtfDate) + " " + this.time.format(dtfTime);
    }

}