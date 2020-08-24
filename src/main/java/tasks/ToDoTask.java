package tasks;

public class ToDoTask extends Task {

    public ToDoTask(String title) {
        super(title, "T");
    }

    public ToDoTask(String title, boolean isDone) {
        super(title, isDone, "T");
    }
}
