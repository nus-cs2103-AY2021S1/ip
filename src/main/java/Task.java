public class Task {
    protected String title;
    protected boolean complete;

    public Task(String title) {
        this.title = title;
        this.complete = false;
    }

    public boolean isDone() {
        return this.complete;
    }

    public void complete() {
        this.complete = true;
    }

    @Override
    public String toString() {
        return this.complete
                ? String.format("[\u2713] %s", this.title)
                : String.format("[\u2717] %s", this.title);
    }
}