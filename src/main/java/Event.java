public class Event extends Task{
    protected String at;

    public Event(String description, String at, Boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public Event markDone() {
        super.markDone();
        return this;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String fileFormattedString() {
        String doneOrNot = isDone ? "1" : "0";
        return "E | " + doneOrNot + " | " + this.name + " | " + this.at;
    }
}
