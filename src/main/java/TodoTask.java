public class TodoTask extends Task {
    public TodoTask(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
