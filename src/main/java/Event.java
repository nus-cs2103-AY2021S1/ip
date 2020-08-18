public class Event extends Task {
    private String atTime;

    Event(String eventTask, String atTime) {
        super(eventTask);
        this.atTime = atTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + atTime + ")";
    }
}
