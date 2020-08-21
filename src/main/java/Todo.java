public class Todo extends Task {

    public Todo(String description, int index) {
        super(description, index);
    }
    @Override
    public String getStatusWithIndex() {
        return String.format("%s. %s%s%s", index, TaskType.TODO, isDone ? super.done : super.start, this.description);
    }
    @Override
    public String toString() {
        return String.format("%s%s%s", TaskType.TODO, isDone ? super.done : super.start, this.description);
    }
}
