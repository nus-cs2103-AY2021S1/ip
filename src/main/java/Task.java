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

    private Tag tag;

    /**
     * Task constructor that creates a task marked as not done.
     *
     * @param desc The description of the task.
     */
    public Task(final String desc) {
        this.desc = desc;
        this.isDone = false;
        this.tag = null;
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
        this.tag = null;
    }

    public Task(final String desc, final boolean isDone, final Tag tag) {
        this.desc = desc;
        this.isDone = isDone;
        this.tag = tag;
    }

    public Task(final String desc, final Tag tag) {
        this.desc = desc;
        this.isDone = false;
        this.tag = tag;
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
        boolean containsTag = !this.tag.toString().isBlank();
        if (containsTag) {
            return "["
                    + this.getStatusIcon() + "] "
                    + this.desc + " #" + this.tag.toString();
        } else {
            return "["
                    + this.getStatusIcon() + "] "
                    + this.desc;
        }
    }
}
