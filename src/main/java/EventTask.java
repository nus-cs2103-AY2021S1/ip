public class EventTask extends Task {
    String timing;

    EventTask(String description, String timing) {
        super(description);
        this.timing = timing;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (at: " + timing + ")";
    }

}