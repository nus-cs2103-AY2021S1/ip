public class Event extends Task {
    private String at;

    private Event(String description, String at) {
        super(description);
        this.at = at;
    }

    protected static Event createEvent(String details) throws InvalidEventException {
        String[] info = details.split("/");
        if (info.length == 1) {
            throw new InvalidEventException();
        }
        String desc = info[0];
        String at = info[1].replaceFirst("at ", "");
        return new Event(desc, at);
    }

    @Override
    public String toSaveString() {
        return (isDone ? 1 : 0) +  "event " + description + "/at " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }
}
