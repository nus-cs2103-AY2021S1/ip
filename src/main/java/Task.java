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

    public Task setDone(boolean b) {
        return new Task(this.name, true);
    }

    public boolean isDone() {
        return done;
    }
}
