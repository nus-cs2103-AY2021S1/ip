package duke.task;

import java.io.Serializable;

/**
 * Describes the Task stored by Duke; is serializable
 */
public class Task implements Serializable {
    private static int taskCount = 0;
    private int taskID;
    private boolean completed;
    private final String description;
    
    private static final String TICK = "\u2713", CROSS = "\u2718";
    
    /**
     * Assigns an ID based on the count of active tasks there currently
     *
     * @param description A string description for that Task
     */
    public Task(String description) {
        this.taskID = ++taskCount;
        this.description = description;
        this.completed = false;
    }
    
    /**
     * Marks a task as completed
     *
     * @return the completed version of that task
     */
    public Task complete() {
        this.completed = true;
        return this;
    }
    
    /**
     * Decreases the taskID by 1 when a Task is deleted
     *
     * @return Task with a lower ID
     */
    public Task decrementID() {
        --this.taskID;
        return this;
    }
    
    /**
     * Decreases the taskCount by 1 when a Task is deleted
     */
    public static void decrementTaskCount() {
        --Task.taskCount;
    }
    
    /**
     * Checks if Task is completed
     *
     * @return True if task is completed
     */
    public boolean isComplete() {
        return this.completed;
    }
    
    public int getID() {
        return this.taskID;
    }
    
    @Override
    public String toString() {
        return "[" + (this.completed
                      ? TICK
                      : CROSS) + "] " + this.description;
    }
}
