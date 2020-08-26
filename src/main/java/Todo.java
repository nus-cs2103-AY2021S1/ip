public class Todo extends Task {
    private static final String SAVE_STRING = "TODO|%s|%s";

    public Todo(String taskName) {
        super(taskName);
    }

    public Todo(boolean isDone, String taskName) {
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
