/**
 * Represents a task.
 *
 * @author Siqi
 * @version 1.1
 * @since 2020-09-08
 */
public abstract class Task {
    /**
     * Task description.
     */
    private String desc;
    /**
     * This states whether task is done.
     */
    private boolean isDone;

    /**
     * Task constructor that creates a task marked as not done.
     *
     * @param desc The description of the task.
     */
    public Task(final String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    /**
     * Task constructor that creates a task marked as done.
     *
     * @param desc      The description of the task.
     * @param isDone    Whether the task is done.
     */
    public Task(final String desc, final boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return (isDone ? "1" : "0");
    }

    public String getDescription() {
        return this.desc;
    }

    /**
     * Marks the task as done.
     *
     * @return This returns the same task that has been marked as done.
     */
    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    /**
     * Formats the task for display to the user.
     */
    abstract public String display();

    @Override
    public String toString() {
        return "["
                + this.getStatusIcon() + "] "
                + this.desc;
    }
}
