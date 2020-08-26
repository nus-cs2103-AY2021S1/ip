public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = null;
    }
    public Task(String description, boolean isDone, String type) {
        this.description = description;
        this.isDone = isDone;
        this.type = type;
    }
    public String getType() {
        return this.type;
    }
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        if (!isDone) {
            this.isDone = true;
        }
    }

    public void markAsDeleted() {
        this.isDone = false;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "[" + this.type + "][" + getStatusIcon() + "] " + this.description;
    }

}
