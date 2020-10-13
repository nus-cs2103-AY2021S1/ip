package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description, "T");
    }
    public Todo(String description, Priority p) {
        super(description, p, "T");
    }
}
