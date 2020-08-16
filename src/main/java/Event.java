public class Event extends Task {

    private String startTime;

    public Event(String taskName, String startTime) {
        super(taskName);
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), startTime);
    }
}
