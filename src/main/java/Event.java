public class Event extends Task {
    private String time;
    public Event(String desc, String time) {
        super(desc);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.time);
    }
}
