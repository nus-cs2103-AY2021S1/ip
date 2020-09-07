package chatbot.data;

public class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, "T", isDone, null);
    }

    public static Todo newTodo(String description) {
        return new Todo(description, false);
    }

    @Override
    public Todo markDone() {
        return new Todo(this.description, true);
    }
}
