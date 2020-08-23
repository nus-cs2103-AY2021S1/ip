public class Event extends Task {
    private String time;
    public Event(String desc, String time) {
        super(desc);
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.time);
    }

    @Override
    public String toFileString() {
        return "E\n"+super.toFileString()+this.time + "\n";
    }
}
