package duke;
public abstract class Task {
    protected String desc;
    protected boolean done;
    Task(String desc) {
        this.desc = desc;
        this.done = false;
    }

    public void markDone() {
        done = true;
    }
    @Override
    public String toString() {
        char sign = (done ? '✓' : '✗');
        return String.format("[%c] %s", sign, desc);
    }

    public abstract String toDisk();
}