public class Task {
    protected String description;
    protected boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void completeTask() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return (this.isDone ? "[✓] " : "[✗] ") + this.description;
    }
}
