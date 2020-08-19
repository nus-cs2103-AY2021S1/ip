public class Task {
    String title;
    boolean complete;

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

    public String toString() {
        return this.complete
                ? String.format("[&#x2713] %s", this.title)
                : String.format("[&#x2717] %s", this.title);
    }
}