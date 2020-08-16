public class Events extends Task {
    private String start;

    public Events(String description, String startTime) {
        super(description);
        this.start = startTime;
    }

    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.start + ")";
    }
}
