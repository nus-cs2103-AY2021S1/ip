package godfather.task;

/**
 * Contains information about an Event, with when it starts and ends and on what date
 */
public class Event extends Task {
    private static final String DELIMITER = "at";
    private static final String TIME_DELIMITER = "-";
    private final String startTime;
    private final String endTime;
    private final String dateString;
    /**
     * Constructs an Event object
     *
     * @param description String description for the event
     * @param dateString  String description for the Event's date
     * @param startTime   String description for when the event starts
     * @param endTime     String description for when the event ends
     * @param newTaskID   ID to be assigned to the new task
     */
    public Event(String description, String dateString, String startTime, String endTime, int newTaskID) {
        super(description, newTaskID);
        this.dateString = dateString;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    /**
     * Creates an Event by extracting out relevant information from the parsed user input
     *
     * @param parsedInput User's input that is parsed by the parser
     *
     * @return Event
     */
    public static Event createEvent(String[] parsedInput, int newTaskID) {
        String description = parsedInput[1];
        String dateString = parsedInput[2];
        String startTime = parsedInput[3];
        String endTime = parsedInput[4];
        return new Event(description, dateString, startTime, endTime, newTaskID);
    }
    @Override
    public boolean isComplete() {
        return super.isComplete();
    }
    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (" + DELIMITER + ":" + this.dateString + " " + this.startTime + TIME_DELIMITER + this.endTime + ")";
    }
}
