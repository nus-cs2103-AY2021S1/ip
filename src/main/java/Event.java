public class Event extends Task {
    private final String timeDescription;

    Event(String taskDescription, String timeDescription) {
        super(taskDescription);
        this.timeDescription = timeDescription;
    }

    public String getTimeDescription() {
        return timeDescription;
    }

    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.timeDescription + ")";
    }
}
