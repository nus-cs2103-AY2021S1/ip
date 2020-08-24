public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return ("[" + this.type + "][" + this.getStatusIcon() + "] " + this.description);
    }

    public String getType() {
        return this.type;
    }
}