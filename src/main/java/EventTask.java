public class EventTask extends Task{
    protected String due;

    public EventTask(String description, String due) {
        super(description);
        this.due = due;
    }

    @Override
    public String fileString() {return "E | " + super.toString() + " | " + due; }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + due + ")";
    }
}
