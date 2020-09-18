package duke;

/**
 * Represent an todo with no deadline
 */
public class Todo extends Task {

    public Todo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * @return the representation of the event when written to disk
     */
    @Override
    public String toDisk() {
        return String.format("todo\n%s\n%d", desc, (isDone ? 1 : 0));
    }
}
