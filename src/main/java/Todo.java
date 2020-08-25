public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String taskSaver() {
        String type = "T";
        return type + "/" + super.taskSaver();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
