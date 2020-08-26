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
    public String toFileString() {
        return "T\n" + super.getDoneStatus() + "\n" + super.toFileString() + "\n\n";
    }
}