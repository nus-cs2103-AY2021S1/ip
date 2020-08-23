public class ToDo extends Task {
    protected ToDo(String description, boolean isDone) {
        super(description, isDone, TaskType.TODO, "-");
    }

    protected ToDo(String description) {
        super(description, false, TaskType.TODO, "-");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
