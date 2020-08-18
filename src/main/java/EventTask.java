public class EventTask extends Task {
    String at;
    static final String TASK_TYPE = "E";

    public EventTask(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)", TASK_TYPE, getStatusIcon(), description, at);
    }
}
