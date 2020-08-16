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

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
