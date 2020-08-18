public class Task {
    private String desc;
    private boolean done;

    public Task(String desc) {
        this.desc = desc;
    }

    public void done() {
        done = true;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", done ? '✓' : '✗', desc);
    }
}
