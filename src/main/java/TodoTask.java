public class TodoTask extends Task {
    public TodoTask(
            Boolean isDone,
            String name
    ) {
        super(isDone, name);
    }

    @Override
    public String toString() {
        return "[T]|" + super.toString();
    }
}
