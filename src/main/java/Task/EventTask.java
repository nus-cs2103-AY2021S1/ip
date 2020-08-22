package Task;

public class EventTask extends Task {
    protected String time;

    public EventTask(String description, String time) {
        super(description);
        this.time = time;
    }

    public EventTask(String description, String time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.time + ")";
    }
}
