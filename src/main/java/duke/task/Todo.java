package duke.task;

public class Todo extends Task {

    /**
     * A Task without deadline or duration.
     * @param name name of Task
     * @param isDone whether Task is done
     */
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Returns String representation of Task presented to user.
     * @return String representation of Task presented to user
     */
    @Override
    public String toString() {
        String doneSymbol = getIsDone() ? "✓" : "✗";
        return String.format("[T][%s] %s", doneSymbol, getName());
    }

    /**
     * Returns String representation of Task to be saved.
     * @return String representation of Task to be saved
     */
    @Override
    public String toSaveString() {
        return String.format("T|%d|%s", getIsDone() ? 1 : 0, getName());
    }
}
