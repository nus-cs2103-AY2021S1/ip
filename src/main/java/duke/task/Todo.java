package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String convertToData() {
        return "T | " + (isDone ? 1 : 0) + " | " + description;
    }
}
