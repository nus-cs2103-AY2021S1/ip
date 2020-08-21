package duke;

public abstract class Task {
    protected String name;
    protected boolean isDone;

    Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Represent task's name and status
     *
     * @return a string that represents the task
     */
    public String showTask() {
        return String.format("[%s] %s", getStatusIcon(), this.name);
    }

    public boolean getStatus() {
        return this.isDone;
    }
    
    public String getDescription() {
        return this.name;
    }
    
    public abstract String getType();
    
    /**
     * Mark the task as done
     *
     * @return true if it is not done before, otherwise false
     */
    public boolean markAsDone() {
        if (this.isDone) {
            return false;
        } else {
            this.isDone = true;
            return true;
        }
    }

//    private String getStatusIcon() {
//        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
//    }
    private String getStatusIcon() {
        return (isDone ? "V" : "X"); //return tick or X symbols
    }
    
    
}
