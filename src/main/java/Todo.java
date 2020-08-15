public class Todo extends Task {

    Todo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[Todo] " + super.toString();
    }
}
