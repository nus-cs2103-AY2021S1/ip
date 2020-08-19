public class ToDo extends Task {
    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone, TaskType.TODO);
    }

    @Override
    public String getTime() {
        throw new IllegalStateException("Impossible method call");
    }
}
