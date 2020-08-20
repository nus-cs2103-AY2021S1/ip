public class Task {
    protected String name;
    protected boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void markAsDone() {
        this.done = true;
    }

    public void markAsUndone() {
        this.done = false;
    }

    @Override
    public String toString() {
        String symbol = this.done ? "\u2713" : "\u2717";
        return "[" + symbol + "] " + this.name;
    }
}
