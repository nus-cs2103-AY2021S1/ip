public class Todo extends Task {

    public Todo(String description) {
        super(description);
        super.type = Task.Type.TODO;
    }
}