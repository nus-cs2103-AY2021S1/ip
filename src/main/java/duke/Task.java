package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
  
    public String getStatusIcon() {
        return isDone
                ? "\u2713"
                : "\u2718";
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getTaskType() {
        return "Task";
    }
    
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
    
    public String toStringInFile() {
        int taskDone = isDone ? 1 : 0;
        return " | " + taskDone + " | " + getDescription();
    }
}
