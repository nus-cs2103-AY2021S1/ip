public class Todo extends Task {
    Todo(String name, boolean isDone) {
        super(name, isDone, "T");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}