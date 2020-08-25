package duke.tasks;

/**
 * Represents to-do tasks created by users.
 *
 */
public class ToDo extends Task{
    /**
     * Creates ToDo object with relevant task name.
     *
     * @param taskName Task name.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Returns String representation of to-do task in backend data storage.
     *
     * @return Representation of to-do task in backend data storage.
     */
    public String getDataStorageName() {
        return "T | " + (super.getStatus() == "\u2713" ? "1" : "0") + " | " + super.getTaskName();
    }

    /**
     * Returns String representation of to-do task to be displayed in task list.
     *
     * @return Representation of to-do task in task list.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
