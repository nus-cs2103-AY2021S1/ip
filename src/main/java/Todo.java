public class Todo extends Task {
    private Todo(String desc) {
        super(desc);
    }

    protected static Todo createTodo(String desc) {
        return new Todo(desc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
