public abstract class Task {
    protected String name;
    protected boolean done;

    Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void complete() {
        this.done = true;
    }

    @Override
    public String toString() {
        if (this.done) {
            return String.format("[✓] %s", name);
        } else {
            return String.format("[✗] %s", name);
        }
    }
}