public class Event extends Task {
    private String time;

    public Event(String description, boolean done, String time) {
        super(description, done);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.time + ")";
    }

    @Override
    public String saveToHardDisk() {
        return "E" + super.saveToHardDisk() + " | " + this.time;
    }
}
