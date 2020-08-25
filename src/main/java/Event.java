import java.time.LocalDateTime;

public class Event extends Task {

   // protected String at;
    private LocalDateTime date;

    public Event(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    public static Event createEvent(String details) {
        if (details == null) {
            throw new DukeException("I need something to work with.");
        }
        String[] detailsArray = details.split("/at", 2);
        try {
            String description = detailsArray[0].trim();
            String dateTimeString = detailsArray[1].trim();
            LocalDateTime dateTime = DateConverter.parseString(dateTimeString);
            return new Event(description, dateTime);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Out of Bounds Exceptions");
        }
    }

    public String encode() {
        return String.format("E|%s|%s|%s", super.isDone ? "Y" : "N", this.date, super.description);
    }

    public static Event decode(String code) throws DukeException {
        if (code.charAt(0) == 'E') {
            String[] content = code.split("\\|", 4);
            if (content.length != 4) {
                throw new Error("data string is not equal to 4");
            }
            Event newEvent = new Event(content[3], DateConverter.parseString(content[2]));
            if (content[1].equals("Y")) {
                newEvent.markAsDone();
            }
            return newEvent;
        } else {
            throw new DukeException("Unable to decode event");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + DateConverter.parseLocalDateTime(this.date) + ")";
    }
}
