public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String taskFileFormat() {
        return "E | " + (super.isDone ? "1 |" : "0 |") + super.description + "|" + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + this.at + ")";
    }
}
