public class Task {
    private String description;
    private boolean done;

    public Task(String description, boolean done) {
        this.description = description;
        this.done = done;
    }

    public void completeTask() {
        this.done = true;
    }

    @Override
    public String toString() {
        String symbol = (this.done ? "[\u2713] " : "[\u2718] ");
        return symbol + this.description;
    }
}
