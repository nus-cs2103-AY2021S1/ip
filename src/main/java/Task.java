public class Task {
    protected String description;
    protected boolean isDone;
    protected int index;

    public Task(String description, int index) {
        this.description = description;
        this.isDone = false;
        this.index = index;
    }

    public String toString() {
        return this.index + ". " + this.description;
    }
}