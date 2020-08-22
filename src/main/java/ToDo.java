public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    protected String taskToText() {
        return "T|" + super.completed + "|" + super.taskName;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
