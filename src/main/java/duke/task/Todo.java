package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String getTaskIdentifier() {
        return "T";
    }
}
