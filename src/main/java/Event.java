public class Event extends Task {
    protected String time;

    public Event(String name, String time, boolean status) {
        super(name, status);
        this.time = time;
    }

    @Override
    public String getStatus() {
        return "[E]" + super.getStatus() + " (at: " + this.time + ")";
    }

    @Override
    public String getTime() {
        return this.time;
    }

    @Override
    public String getType() {
        return "E";
    }
}
