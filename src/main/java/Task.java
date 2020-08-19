public class Task {
    private static final String tick = "\u2713";
    private static final String cross = "\u2718";
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
        return boxFormat(status) + " " + description;
    }

    private String boxFormat(String symbol) {
        return String.format("[%s]", symbol);
    }
}
