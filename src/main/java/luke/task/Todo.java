package luke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(TaskType.TODO, description);
    }

    @Override
    public String toDataString() {
        return String.format("T|%s|", super.toDataString());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
