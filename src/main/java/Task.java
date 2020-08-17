public class Task {
    private final String name;
    private final boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public String getStatusIcon() {
        return (done ? "\u2713" : "\u2718");
    }

    public Task setDone(boolean b) {
        return new Task(this.name, true);
    }

    public boolean isDone() {
        return done;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        String statusIcon = "[" + this.getStatusIcon() + "] ";
        return statusIcon + this.getName();
    }
}
