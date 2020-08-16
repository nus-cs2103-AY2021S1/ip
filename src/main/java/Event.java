public class Event extends Task {

    protected String at;

    public Event(String description, String at) throws WrongFormatException {
        super(description, "[E]", "event");
        this.at = at;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + at + ")";
    }
}