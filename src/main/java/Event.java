public class Event extends Task {
    private String at;

    private Event(String description, String at) {
        super(description);
        this.at = at;
    }

    protected static Event createEvent(String details) {
        String[] info = details.split("/");
        String desc = info[0];
        String at = info[1].replaceFirst("at ", "");
        return new Event(desc, at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }
}
