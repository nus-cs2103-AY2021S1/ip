public class DeadlineTask extends Task {
    private String by;
    static final String TASK_TYPE = "D";

    public DeadlineTask(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)", TASK_TYPE, getStatusIcon(), description, by);
    }
}
