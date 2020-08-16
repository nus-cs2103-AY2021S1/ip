/**
 * This class encapsulates the functionality of a task
 */
public class Task {
    private String task;

    /**
     * Constructor for the class
     * @param task The task to be completed
     */
    public Task(String task) {
        this.task = task;
    }

    /**
     * Getter for the task
     * @return The task to be completed
     */
    public String getTask() {
        return task;
    }

    @Override
    public String toString() {
        return task;
    }
}
