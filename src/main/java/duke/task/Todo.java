package duke.task;

/**
 * Todo task that takes in a description.
 */
public class Todo extends Task {

    public Todo(String task) {
        super(task);
    }
    public Todo(String task, boolean done) {
        super(task, done);
    }
    public Todo(String task, boolean done, String note) {
        super(task, done, note);
    }
    @Override
    public String getSaveString() {
        return "[T] " + super.getSaveString();
    }

    @Override
    public String toString() {
        return "[Todo] " + super.toString();
    }
}
