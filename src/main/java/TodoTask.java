public class TodoTask extends Task {
    public TodoTask(String name) {
        super(name);
    }
    public TodoTask(String name, int hasCompleted) {
        super(name, hasCompleted);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
