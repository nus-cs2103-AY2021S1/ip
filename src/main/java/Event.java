public class Event extends Task {

    public Event(String eventName, String eventTime) {
        super(eventName, eventTime);
    }

    @Override
    public String writeToFile() {
        return "event" + "|" + this.getStatusSymbol() + "|"
                + this.taskName + "|" + this.timePeriod;
    }

    @Override
    public String toString() {
        TimeParser timeParser = new TimeParser(localDate);
        String formattedTime = timeParser.getFormattedTime();
        return "[E]" + super.toString() + " (at: " + formattedTime + ")";
    }
}
