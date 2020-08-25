import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private String TIME12H;
    private LocalDate DATE;

    private Event(String description, String TIME12H, LocalDate DATE) {
        super(description);
        this.TIME12H = TIME12H;
        this.DATE = DATE;
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
            throw new InvalidEventException();
        }
    }

    @Override
    public boolean isDueOn(LocalDate date) {
        return this.DATE.equals(date);
    }

    @Override
    public String toString() {
        String formattedDate = DateTimeParsing.localDateToString(DATE);
        return "[E]" + super.toString() + "(at: " + formattedDate + " " + TIME12H + ")";
    }
}
