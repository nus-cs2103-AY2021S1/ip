public class Todo extends Task {
    protected String todo;

    public Todo(String todo) {
        super(todo);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}