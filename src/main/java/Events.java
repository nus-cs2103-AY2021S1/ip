public class Events extends Task {
    private String startTime;

    public Events(String description, String startTime) {
        super(description);
        this.startTime = startTime;
    }

    private Events(String description, String startTime, boolean bool) {
        super(description, bool);
        this.startTime = startTime;
    }

    @Override
    public Events markDone() {
        return new Events(this.description, this.startTime, true);
    }

    @Override
    public String toString() {
        if (this.isComplete) {
            return "[E][\u2713] " + this.description + "(at:" + this.startTime + ")";
        } else {
            return "[E][\u2718] " + this.description + "(at:" + this.startTime + ")";
        }
    }
}
