public class Task {
    protected String description;
    protected boolean isDone;
    protected String storeAs;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
        this.updateDoneForStoreAs();
    }

    private void updateDoneForStoreAs() {
        this.storeAs = this.storeAs.substring(0, 2) + "1" + this.storeAs.substring(3);
    }

    public String getStoreAs() {
        return this.storeAs;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

}
