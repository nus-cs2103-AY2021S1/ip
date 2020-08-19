public class Event extends Task {
    String time;

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String getStatus() {
        return "[E]" + super.getStatus() + " (at: " + this.time + ")";
    }
}
