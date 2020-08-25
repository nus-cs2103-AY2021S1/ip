public class Event extends Task {

    protected String at;

    public Event(String desc, String at) {
        super(desc);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String toDisk() {
        return String.format("event\n%s\n%d\n%s", desc, (done == true ? 1 : 0), at);
    }
}