public class ToDo extends Task {
    public ToDo(String toDoName) {
        super(toDoName);
    }

    @Override
    public String getInfo() {
        return String.format("\nT | %d | %s", isDone ? 1 : 0, taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
