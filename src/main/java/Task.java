public class Task {
    protected String desc;
    protected boolean isDone;

    public Task(String desc) {
        this.desc = desc;
    }

    private Task(String desc, boolean done) {
        this.desc = desc;
        this.isDone = done;
    }

    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", isDone ? '\u2713' : '\u2717', desc);
    }

    public String toSaveString() {
        return "error";
    }
}
