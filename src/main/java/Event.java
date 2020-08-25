import java.time.LocalDateTime;

/**
 * Event class creates a type of Task called event which contains a date and description of the task
 */
public class Event extends Task {


    private LocalDateTime date;

    public Event(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }
    /**
     * creates a Event from string
     * @param details input from user
     * @return Event object
     */
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
    /**
     * convertes a String into a condensed form
     * @return condesnsed form of inputted string
     */
    public String encode() {
        return String.format("E|%s|%s|%s", super.isDone ? "Y" : "N", this.date, super.description);
    }
    /**
     * unravels encoded Strings
     * @param code String that has been previously encoded()
     * @return Event object
     * @throws DukeException in the event it is unable to decode the string
     */
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
    /**
     * overrides Event String output to be formatted
     * @return String of formatted Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + DateConverter.parseLocalDateTime(this.date) + ")";
    }
}
