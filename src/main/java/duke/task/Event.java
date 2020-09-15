package duke.task;

public class Event extends Task {
    protected String eventDate;
    public Event(String description, String eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    public String saveEvent() {
        return String.format("E | %d | %s | %s",
                this.getIsDone() ? 1 : 0, this.getDescription(), this.eventDate);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.eventDate + ")";
    }
}