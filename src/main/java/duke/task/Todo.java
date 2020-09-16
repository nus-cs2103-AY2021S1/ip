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
     * Compares two todos and check if they are the same
     * @param o object to compare equality
     * @return true if the todos are the same
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Todo)) {
            return false;
        }
        Todo t = (Todo) o;
        return getName().equals(t.getName());
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
