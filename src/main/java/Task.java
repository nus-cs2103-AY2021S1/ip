public class Task {
    protected String description;
    protected boolean isDone;
    protected int index;

    public Task(String description, int index) {
        this.description = description;
        this.isDone = false;
        this.index = index;
    }

    public String getStatusWithIndex() {
        return isDone ? index + ". " + "[\u2713] " + this.description
                : index + ". " + "[\u2718] " + this.description; //return tick or X symbols
    }

    public String toString() {
        return (isDone ? "[\u2713] " + this.description : "[\u2718] " + this.description);
    }
}