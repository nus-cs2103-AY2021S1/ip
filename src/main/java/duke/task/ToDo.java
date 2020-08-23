package duke.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toData() {
        String isDone = super.isDone ? "1" : "0";
        String separator = "~";
        return "T" + separator + isDone + separator + super.description + "\n";
    }
}
