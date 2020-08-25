public class Todo extends Task {

    public Todo(String description) {
        super(description);
        super.type = Task.Type.TODO;
    }

    public Todo(String description, boolean isDone) {
        super(description);
        super.isDone = isDone;
        super.type = Task.Type.TODO;
    }
}