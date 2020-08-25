package duke.task;

public class ToDo extends Task {
    public ToDo(String details) {
        super(details);
    }

    public ToDo(String details, boolean isDone) {
        super(details, isDone);
    }

    @Override
    public String store() {
        String done = this.isDone ? "T " : "F ";
        return "T " + done + this.details + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
