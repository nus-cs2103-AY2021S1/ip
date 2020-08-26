package duke.task;

import java.io.Serializable;

public class Task implements Serializable {
    private static int taskCount = 0;
    private int taskID;
    private boolean completed;
    private final String description;
    
    private static final String TICK = "\u2713", CROSS = "\u2718";
    
    public Task(String description) {
        this.taskID = ++taskCount;
        this.description = description;
        this.completed = false;
    }
    public String getDescription() {
        return this.description;
    }
    public Task complete() {
        this.completed = true;
        return this;
    }
    
    public Task decrementID() {
        --this.taskID;
        return this;
    }
    
    public static void decrementTaskCount() {
        --Task.taskCount;
    }
    
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
