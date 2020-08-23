public class Event extends Task {

    protected DukeDate at;

    public Event(String description, String at) {
        super(description);
        this.at = new DukeDate(at);
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}