public class Task {
    String title;
    boolean complete;

    public Task(String title) {
        this.title = title;
        this.complete = false;
    }

    public isDone() {
        return this.complete;
    }

    public complete() {
        this.complete = true;
    }

    public String toString() {
        return this.complete
                ? String.format("[✓] %s", this.title)
                : String.format("[✗] %s", this.title)
    }
}