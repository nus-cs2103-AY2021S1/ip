package tasklist;

/**
 * Task class is the super class of all Task types.
 * The subclasses are Todo, Event and Deadline.
 * @author Maguire Ong
 */

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks if the task isDone, if isDone, a tick will be returned
     * else, a cross will be returned.
     * @return Lateral location.
     * @throws IllegalArgumentException  If zone is <= 0.
     */
    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
