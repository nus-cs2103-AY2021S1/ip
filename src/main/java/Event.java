public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toFileString() {
        String done = super.isDone ? "1" : "0";
        return "E | " + done + " | " + super.description + " | " +  at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}