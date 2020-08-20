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
}
