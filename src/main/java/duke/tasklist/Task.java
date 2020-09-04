package duke.tasklist;

import java.util.ArrayList;

/**
 * Task class is the super class of all Task types.
 * The subclasses are Todo, Event and Deadline.
 * @author Maguire Ong
 */

public class Task {
    protected String description;
    protected boolean isDone;
    protected ArrayList<String> tags;

    public Task(String description, ArrayList<String> tags) {
        this.description = description;
        this.isDone = false;
        this.tags = tags;
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

    public ArrayList<String> getTags() {
        System.out.println(this.tags);
        return this.tags;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
