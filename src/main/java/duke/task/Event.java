public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, int status, String at) {
        super(description, status);
        this.at = at;
    }

    @Override
    public String saveText(int status) {
        return "E | " + status + " | " + description + " | " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
