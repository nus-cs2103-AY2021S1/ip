public class Event extends Task {
    private String timePeriod;

    public Event(String name, String timePeriod) {
        super(name);
        this.timePeriod = timePeriod;
    }

    public Event(String name, String timePeriod, boolean done) {
        super(name, done);
        this.timePeriod = timePeriod;
    }

    @Override
    public String toFileFormat() {
        return "E" + " | " + super.toFileFormat() + " | " + this.timePeriod + "\n";
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.timePeriod);
    }
}
