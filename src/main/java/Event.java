public class Event extends Task {
    private String timePeriod;

    public Event(String eventName, String timePeriod) {
        super(eventName);
        this.timePeriod = timePeriod;
    }

    @Override
    public String writeToFile() {
        return "event" + "|" + this.getStatusSymbol() + "|"
                + this.taskName + "|" + this.timePeriod;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + timePeriod + ")";
    }
}
