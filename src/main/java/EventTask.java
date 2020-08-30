public class EventTask extends Task{
    protected DukeDate due;

    public EventTask(String description, String due) {
        super(description);
        this.due = new DukeDate(due);
    }

    @Override
    public String fileString() {return "E | " + super.fileString() + " | " + due; }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + due + ")";
    }
}
