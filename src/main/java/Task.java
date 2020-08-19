public class Task {
    private final String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
    }

    public boolean isDone() {
        return done;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        String doneSymbol = isDone() ? "✓" : "✗";
        return String.format("[%s] %s", doneSymbol, getName());
    }
}
