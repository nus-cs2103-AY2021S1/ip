public class Todo extends Task {
    boolean done;
    String task;

    Todo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[Todo] " + super.toString();
    }
}
