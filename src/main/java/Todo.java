public class Todo extends Task {
    public Todo(String task) {
        super(task);
    }

    public Todo(String task, boolean isDone) {
        super(task, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
