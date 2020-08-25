public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name, boolean status) {
        this.name = name;
        this.isDone = status;
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

    public String getType() {
        return "type unknown";
    }

    public String getTime() {
        return "";
    }

    public boolean isComplete() {
        return this.isDone;
    }
}
