package duke.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String output() {
        return "T" + super.output() + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
