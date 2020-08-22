public class Event extends Task{

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(boolean isDone,String description, String at) {
        super(description);
        this.at = at;
        this.isDone = isDone;
    }

    @Override
    public String toFileStringFormat() {
        return String.format("E / %d / %s / %s",isDone ? 1 : 0, this.desciption,this.at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
