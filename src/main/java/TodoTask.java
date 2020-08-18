public class TodoTask extends Task {
    static final String TASK_TYPE = "T";

    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", TASK_TYPE, getStatusIcon(), description);
    }
}
