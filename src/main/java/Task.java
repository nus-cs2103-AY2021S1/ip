public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = null;
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

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return  "[" + this. type + "]" + this.description;
    }

}
