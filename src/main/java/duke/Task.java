package duke;

import java.util.HashMap;

/**
 * Is responsible for basic functionality of task.
 * They are different types of task.
 */
public class Task {
    public boolean isDone;
    protected String description;
    protected Integer priorityLevel;
    private HashMap<Integer, String> priorityIcons = new HashMap<Integer, String> () {
        {
            put(1,"!");
            put(2, "!!");
            put(3, "!!!");
        }
    };

    /**
     * Constructor of Task.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status icon for task for indicating whether task is done or not.
     *
     * @return status icon indicating whether task is done or not.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getPriorityIcon() {
        return priorityIcons.get(priorityLevel);
    }

    /**
     * Mark task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    public Integer getPriorityLevel() {
        return this.priorityLevel;
    }

    public void setPriorityLevel(Integer priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    /**
     * Returns string representation of task in string format.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        if (priorityLevel != null) {
            // task with priority level
            return String.format("[%s] [%s] %s", getStatusIcon(), getPriorityIcon(), description);
        } else {
            // task without priority level
            return String.format("[%s] %s", getStatusIcon(), description);
        }
    }
}
