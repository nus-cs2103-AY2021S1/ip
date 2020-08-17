public abstract class Task {

    public static int numberOfTask = 0;

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numberOfTask++;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean checkStatus() {
        return this.isDone;
    }

    public abstract String getTaskType();


}
