import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private final String TIME12H;
    private final LocalDate DATE;

    private Deadline(String description, String time12h, LocalDate date) {
        super(description);
        this.TIME12H = time12h;
        this.DATE = date;
    }

    protected static Deadline createDeadline(String details) throws InvalidDeadlineException {
        String[] info = details.split("/");
        if (info.length == 1) {
            throw new InvalidDeadlineException();
        }
        String desc = info[0];
        String[] dateTime = info[1].replaceFirst("by ", "").split(" ");
        try {
            LocalDate date = DateTimeParsing.parseDate(dateTime[0]);
            String time12h = DateTimeParsing.parse24HTime(dateTime[1]);
            return new Deadline(desc, time12h, date);
        } catch(DateTimeParseException | NumberFormatException e) {
            throw new InvalidDeadlineException();
        }
    }

    @Override
    public boolean isDueOn(LocalDate date) {
        return this.DATE.equals(date);
    }

    @Override
    public String toString() {
        String formattedDate = DateTimeParsing.localDateToString(DATE);
        return "[D]" + super.toString() + "(by: " + formattedDate + " " + TIME12H + ")";
    }
}
