public class Task {
    private static final String tick = "[✓]";
    private static final String cross = "[✗]";
    private String description;
    private boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public void markDone() {
        this.isDone = true;
    }

    public String toString() {
        String status = isDone ? tick : cross;
        return status + " " + description;
    }
}
