import java.util.UUID;

public class Task {
    
    protected String taskType;
    protected String description;
    protected boolean isDone;
    protected String uniqueId;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    
    public Task(String uniqueId, boolean isDone, String description) {
        this.uniqueId = uniqueId;
        this.isDone = isDone;
        this.description = description;
    }

    public void markDone() {
        isDone = true;
    }
    
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or cross symbols
    }
    
    public String getDescription() {
        return description;
    }
    
    public boolean getDone() {
        return isDone;
    }
    
    public String getTaskType() {
        return taskType;
    }
    
    public void generateUniqueId() {
        this.uniqueId = UUID.randomUUID().toString();
    }
    
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getUniqueID() {
        return this.uniqueId;
    }
    
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}