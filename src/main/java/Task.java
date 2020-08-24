public class Task {
    protected boolean completionStatus;
    protected String taskName;

    public Task(String name) {
        this.taskName = name;
        this.completionStatus = false;
    }

    public Task markAsDone(){
        this.completionStatus = true;
        return this;
    }

    public String getStatusIcon() {
        return (completionStatus ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public boolean isToday(){
        return false;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.taskName;
    }
}
