public class Event extends Task{
    private final String time;

    protected Event(String description, String time, boolean isDone) {
        super(description, isDone, TaskType.EVENT, time);
        this.time = time;
    }

    protected Event(String description, String time) {
        super(description, false, TaskType.EVENT, time);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
