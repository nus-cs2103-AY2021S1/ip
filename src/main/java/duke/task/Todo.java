package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toFileString() {
        String done = super.isDone ? "1" : "0";
        return "T | " + done + " | " + super.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}