/**
 * Represents a todo task.
 */
public class ToDo extends Task {

    private final String task;

    protected ToDo(String task) {
        this.task = task;
    }

    /**
     * Returns the task description of this ToDo.
     *
     * @return String of task description.
     */
    @Override
    protected String getTask() {
        return this.task;
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s",
                super.isDone() ? "\u2713" : "\u2717",
                getTask());
    }
}
