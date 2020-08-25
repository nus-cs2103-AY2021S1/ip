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
    
    public static Event createEvent(String[] parsedOutput) {
        String description = parsedOutput[1];
        String dateString = parsedOutput[2];
        String startTime = parsedOutput[3];
        String endTime = parsedOutput[4];
        return new Event(description, dateString, startTime, endTime);
    }
    
    @Override
    protected boolean isComplete() {
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
