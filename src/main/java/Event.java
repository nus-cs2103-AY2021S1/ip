public class Event extends Task {

    protected String timeOfEvent;

    public Event(String description, String timeOfEvent) {
        super(description);
        this.timeOfEvent = timeOfEvent;
    }

    public Event(String description, boolean isDone, String timeOfEvent) {
        super(description, isDone);
        this.timeOfEvent = timeOfEvent;
    }

    @Override
    public String[] serialize() {
        String[] output = new String[4];
        output[0] = this.isDone
                ? "1"
                : "0";
        output[1] = "Event";
        output[2] = description;
        output[3] = timeOfEvent;

        return output;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + timeOfEvent + ")";
    }
}
