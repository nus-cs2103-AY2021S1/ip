public class Event extends Task{
    private String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public Event(String description, boolean isDone) {
        super(description, isDone);
    }

    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + time + ")";
    }
}
