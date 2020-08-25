public class TodoTask extends Task {
    static final String TASK_TYPE = "T";

    public TodoTask(String description) {
        super(description);
    }

    public TodoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", TASK_TYPE, getStatusIcon(), description);
    }

    @Override
    public String toDBString() {
        return TASK_TYPE + "~" + super.toDBString();
    }
}
