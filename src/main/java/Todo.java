public class Todo extends Task {
    Todo(String taskName) {
        super(taskName);
    }

    Todo(String taskName, boolean isCompleted) {
        super(taskName,isCompleted);
    }

    @Override
    public Todo setTaskAsCompleted() {
        return new Todo(taskName,true);
    }

    @Override
    public String toString() {
        return String.format("%s%s", "[T]", super.toString());
    }
}
