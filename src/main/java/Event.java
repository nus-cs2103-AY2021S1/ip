public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.type = "E";
    }

    @Override
    public String toString() {
        return "[" + this. type + "]" + super.toString() + "(at:" + at + ")";
    }
}