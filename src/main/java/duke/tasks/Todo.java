package duke.tasks;

/**
 * The Todo class implements a Todo task with
 * task description.
 *
 * @author Amy Lim Zhi Ting
 * @version v0.1
 */
public class Todo extends Task {

    /**
     * Instantiates a Todo task.
     *
     * @param description String description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        if (!priority.equals(Priority.NONE)) {
            return "[T]" + super.toString() + " <" + this.priority + ">";
        } else {
            return "[T]" + super.toString();
        }
    }

    /**
     * Returns string of this task to be stored in the hard disk.
     *
     * @return String task description to be stored in hard disk.
     */
    public String storedTaskString() {
        return "T" + "!@#" + super.storedTaskString()
                + "!@#" + this.priority;
    }
}
