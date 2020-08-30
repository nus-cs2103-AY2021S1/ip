public class Event extends Task {
    private String time;

    public Event(String command, String time) {
        super(command);
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + time + ")";
    }
}