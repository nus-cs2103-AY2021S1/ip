public class EventTask extends Task {
    String at;
    static final String TASK_TYPE = "E";

    public EventTask(String description, String at) {
        super(description);
        this.at = at;
    }

    public EventTask(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)", TASK_TYPE, getStatusIcon(), description, at);
    }

    public String toDBString() {
        return TASK_TYPE + "~" + super.toDBString() + "~" + at;
    }
}
