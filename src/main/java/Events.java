public class Events extends Task {
    private String startTime;

    public Events(String description, String startTime) {
        super(description);
        this.startTime = startTime;
    }

    public Events(String description, String startTime, boolean bool) {
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
            return "[E][✓] " + this.description + "(at:" + this.startTime + ")";
        } else {
            return "[E][✗] " + this.description + "(at:" + this.startTime + ")";
        }
    }
}
