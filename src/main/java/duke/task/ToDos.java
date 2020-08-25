package duke.task;

/**
 * The class Events denotes a Events task.
 *
 * @author Alvin Chee
 */
public class ToDos extends Task {
    /**
     * Constructs a todos task
     *
     * @param task  Task description information.
     */
    public ToDos(String task) {
        super(task, TaskType.TODO);
    }

    /**
     * Return a done todos task.
     *
     * @return A done todos task.
     */
    @Override
    public ToDos doneTask() {
        super.done = true;
        return this;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
