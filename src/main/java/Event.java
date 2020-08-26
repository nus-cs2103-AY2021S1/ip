public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toData() {
        return checkDone()
                ? "E/1/" + getDescription() + "/" + this.at
                : "E/0/" + getDescription() + "/" + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
