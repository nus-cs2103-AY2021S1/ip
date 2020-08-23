package duke.Task;

public class ToDo extends Task {
    public ToDo(String taskDescription) {
        super(taskDescription);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
