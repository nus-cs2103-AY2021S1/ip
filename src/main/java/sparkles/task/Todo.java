package sparkles.task;

/**
 * Represent a Todo object.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Package the deadline to a format used to store in the task.txt.
     * A file in the local disk to store tasks.
     * @return
     */
    @Override
    public String diskFormat() {
        return "     T | " + super.diskFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}