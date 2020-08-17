abstract class Task {
    protected String description;
    protected boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected void completeTask() {
        this.isDone = true;
    }

    protected String getStatusIcon() {
        return this.isDone ? "[✓] " : "[✗] ";
    }

    @Override
    public String toString() {
        return (this.isDone ? "[✓] " : "[✗] ") + this.description;
    }
}
