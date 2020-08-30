public class EventTask extends Task{
    protected DukeDate due;

    public EventTask(String description, String due) {
        super(description);
        this.due = new DukeDate(due);
    }
    public EventTask(String description, String due, boolean isCompleted) {
        super(description);
        this.due = new DukeDate(due);
        this.isCompleted = isCompleted;
    }

    @Override
    public String fileString() {return "E | " + super.fileString() + " | " + due; }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + due + ")";
    }
}
