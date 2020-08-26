public class TodoTask extends Task {
    private static final String SAVE_STRING = "TODO|%s|%s";

    public TodoTask(String taskName) {
        super(taskName);
    }

    public TodoTask(boolean isDone, String taskName) {
        super(isDone, taskName);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String toSaveString() {
        return String.format(SAVE_STRING, super.isDone, super.taskName);
    }
}
