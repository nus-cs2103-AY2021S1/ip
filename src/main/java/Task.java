public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getStatus() {
        return this.isDone? ("[\u2713] " + toString()) : ("[\u2718] " + toString());
    }

    public void checkTask() {
        this.isDone = !this.isDone;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
