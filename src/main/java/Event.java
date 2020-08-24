import java.time.LocalDateTime;

public class Event extends Task {

    private static final String EVENT_DELIMITER = "/at";

    private LocalDateTime dateTime;

    public Event(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public static Event createTask(String details) {
        if (details == null) {
            throw new DukeException("I need something to work with.");
        }
        String[] detailsArray = details.split(EVENT_DELIMITER, 2);
        try {
            String description = detailsArray[0].trim();
            String dateTimeString = detailsArray[1].trim();
            LocalDateTime dateTime = DateParser.parseString(dateTimeString);
            return new Event(description, dateTime);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Wow that sure is one long event...");
        }
    }

    @Override
    public String toString() {
        return "[E]" +  super.toString() + " (at: " + DateParser.parseLocalDateTime(this.dateTime) + ")";
    }
}
