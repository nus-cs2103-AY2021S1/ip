package duke;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toDisplayString() {
        return "[T]" + super.toDisplayString();
    }

    @Override
    public String toSavedString() {
        return "T" + " | " + super.toSavedString();
    }

}
