public class Event extends Task {

    public String dateTime;

    public Event(String name, String dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    public Event(boolean isDone, String name, String dateTime) {
        super(isDone, name);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String.format("%s%s (at: %s)", "[E]", super.toString(), dateTime);
    }
}
