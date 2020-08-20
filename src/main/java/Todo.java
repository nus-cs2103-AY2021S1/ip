public class Todo extends Task {
    public Todo(String description, TaskType taskType) {
        super (description, taskType);
    }

    @Override
    public String toString() {
        return "[Todo]" + super.toString();
    }
}
