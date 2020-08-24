public class Todo extends Task {
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    public String toSaveData() {
        return "T | " + super.toSaveData();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
