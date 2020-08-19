public class Todo extends Task {

    Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[TODO]" + super.toString();
    }
}
