public abstract class Task {
    private final String name;
    private boolean done;

    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
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

    public abstract String toSaveString();
}
