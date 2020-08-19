public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        // By default print task name
        return "[T]" + super.toString();
    }
}
