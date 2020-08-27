package duke;
public class Event extends Task {
    String description;
    String time;
    boolean isDone;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String getStatus() {
        return "[E]" + super.getStatus() + " (at: " + time + ")";
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "|" + this.time;
    }
}
