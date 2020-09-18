/**
 * Task object contains a name or description of the task to be done and
 * an indicator if it has been completed.
 *
 * @author Hakiem Rasid
 */

public class Task {
    private String name;
    private boolean done;

    /**
     * Constructor for Task object
     *
     * @param name Description or name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.done = false;

    }

    /**
     * Marks the task as completed.
     */
    public void completeTask() {
        this.done = true;
    }

    /**
     * Returns name/description of the task.
     *
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns boolean value to determine if task has been completed.
     *
     * @return True if task is completed. False otherwise.
     */
    public boolean isDone() {
        return this.done;
    }

    /**
     * Prints a string representation of the Task object with a tick if the
     * task has been competed and a cross otherwise.
     *
     * @return String representation of Task object.
     */
    public String printTask() {
        StringBuilder out = new StringBuilder();

        if (this.isDone()) {
            out.append("[/] ");
        } else {
            out.append("[X] ");
        }
        out.append(this.getName());
        return out.toString();
    }
}