public class Todo extends Task {
    public Todo(String description, int index, boolean isDone) {
        super(description, index, isDone);
        super.type = TaskType.TODO;
    }
    @Override
    public String getStatusWithIndex() {
        return String.format("%s. %s%s%s", index, super.type, isDone ? super.done : super.start, this.description);
    }
    @Override
    public String toString() {
        return String.format("%s%s%s", super.type, isDone ? super.done : super.start, this.description);
    }
}
