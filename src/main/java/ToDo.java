/**
 * ToDo class.
 * A ToDo is a task without any date/time attached to it.
 */
public class ToDo extends Task {
    private ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a new uncompleted ToDo.
     * @param description Description of ToDo.
     * @return New uncompleted ToDo.
     */
    public static ToDo getToDo(String description) {
        return new ToDo(description, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task markComplete() {
        return new ToDo(description, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDataString() {
        return String.format("T|%d|%s", isDone ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
