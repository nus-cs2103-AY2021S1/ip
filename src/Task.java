public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getIcon() {
        return (isDone ? "[✓]" : "[✗]");
    }

    public void done() {
        this.isDone = true;
    }

    public void markAsDone() {
        done();
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.getIcon(), description);
    }

}
