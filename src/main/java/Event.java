public class Event extends Task {

    private String date;

    public Event(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), date);
    }
}
