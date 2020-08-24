package duke.tasks;

public class Task {
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
    
    protected Task complete() {
        this.completed = true;
        return this;
    }
    
    protected Task decrementID() {
        --this.taskID;
        return this;
    }
    
    protected static void decrementTaskCount() {
        --Task.taskCount;
    }
    
    protected boolean isComplete() {
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
