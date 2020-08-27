package duke.Task;

/**
 * Todo task that takes in a description.
 */
public class Todo extends Task {

    public Todo(String task) {
        super(task);
    }

    public Todo(String task, boolean done) { super(task, done);}

    @Override
    public String getSaveString() {
        return "[T] " + super.getSaveString();
    }

    @Override
    public String toString() {
        return "[Todo] " + super.toString();
    }
}
