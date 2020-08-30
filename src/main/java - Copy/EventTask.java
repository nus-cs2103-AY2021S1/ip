public class EventTask extends Task{
    protected String due;

    public EventTask(String description, String due) {
        super(description);
        this.due = due;
    }
    public EventTask(String description, String due, boolean isCompleted) {
        super(description);
        this.due = due;
        this.isCompleted = isCompleted;
    }

    @Override
    public String fileString() {return "E | " + super.fileString() + " | " + due; }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + due + ")";
    }
}
