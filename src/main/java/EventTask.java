public class EventTask extends Task {
    private String timeRange;
    public EventTask(String taskName, String timeRange) {
        super(taskName);
        this.timeRange = timeRange;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.timeRange);
    }
}
