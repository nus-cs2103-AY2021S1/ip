public class Todo extends Task {
    Todo(String name) {
        super(name);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}