abstract public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        isDone = true;
    }

    abstract public String saveText();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
