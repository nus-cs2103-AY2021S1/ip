public class Task {
    protected String taskDescription;
    protected boolean isDone;

    public Task(String taskDescription, boolean isDone) {
        this.taskDescription = taskDescription;
        this.isDone = isDone;
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }


    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] "
                + this.getTaskDescription();
    }
    
    public String toWrite() {
        return "T | " + (this.isDone ? '1' : '0')  + " | " + this.taskDescription;
    }
}
