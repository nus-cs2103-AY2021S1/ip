public class Event extends Task {
    private String time;

    public Event(String title, String time) {
        super(title);
        this.time = time;
    }

    @Override
    public String toString() {
        return this.complete
                ? String.format("[E][\u2713] %s (at: %s)", this.title, this.time)
                : String.format("[E][\u2717] %s (at: %s)", this.title, this.time);
    }
}