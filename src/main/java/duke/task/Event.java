package duke.task;

/**
 * Contains information about an Event, with when it starts and ends and on what date
 */
public class Event extends Task {
    private static final String DELIMITER = "at",
            TIME_DELIMITER = "-";
    private final String startTime,
            endTime,
            dateString;
    
    public Event(String description, String dateString, String startTime, String endTime) {
        super(description);
        this.dateString = dateString;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    /**
     * Creates an Event by extracting out relevant information from the parsed user input
     * @param parsedInput Parser's output
     * @return Event
     */
    public static Event createEvent(String[] parsedInput) {
        String description = parsedInput[1];
        String dateString = parsedInput[2];
        String startTime = parsedInput[3];
        String endTime = parsedInput[4];
        return new Event(description, dateString, startTime, endTime);
    }
    
    @Override
    public boolean isComplete() {
        return super.isComplete();
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (" + DELIMITER + ":"
                + this.dateString + " "
                + this.startTime + TIME_DELIMITER + this.endTime + ")";
    }
}
