public abstract class Task {
    protected String desc;
    protected boolean isDone;
    protected TypeOfTask typeOfTask;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }
    
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + desc;
    }
}
