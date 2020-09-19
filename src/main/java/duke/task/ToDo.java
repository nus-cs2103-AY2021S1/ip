package duke.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public String formatToDo() {
        return String.format("T | %d | %s", this.getIsDone() ? 1 : 0,
                this.getDescription());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}