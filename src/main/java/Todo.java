public class Todo extends Task {

    protected static final String TASK_TYPE = "T";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getSaveFormat() {
        return String.format("%s | %s", Todo.TASK_TYPE, super.getSaveFormat());
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", Todo.TASK_TYPE, super.toString());
    }
}
