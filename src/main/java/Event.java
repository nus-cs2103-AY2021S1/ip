import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private String time12h;
    private LocalDate date;

    private Event(String description, String time12h, LocalDate date) {
        super(description);
        this.time12h = time12h;
        this.date = date;
    }

    protected static Event createEvent(String details) throws InvalidEventException {
        String[] info = details.split("/");
        if (info.length == 1) {
            throw new InvalidEventException();
        }
        String desc = info[0];
        String[] dateTime = info[1].replaceFirst("at ", "").split(" ");
        try {
            LocalDate date = DateTimeParsing.parseDate(dateTime[0]);
            String time12h = DateTimeParsing.parse24HTime(dateTime[1]);
            return new Event(desc, time12h, date);
        } catch(DateTimeParseException | NumberFormatException e) {
            e.printStackTrace();
            throw new InvalidEventException();
        }
    }

    @Override
    public String toString() {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + "(at: " + formattedDate + " " + time12h + ")";
    }
}
