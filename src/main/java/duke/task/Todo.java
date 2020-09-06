package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
        super.type = "todo";
    }

    public Todo(String description, boolean isDone) {
        this(description);
        super.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", (
                super.isDone ? "\u2713" : "\u2718"),
                super.description);
    }
}
