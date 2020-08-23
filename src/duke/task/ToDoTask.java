package duke.task;

public class ToDoTask extends Task {
    public ToDoTask(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
