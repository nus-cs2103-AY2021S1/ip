public class Task {
    protected String name;
    protected boolean done;

    Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        String doneString = (done == true ? "✓" : "✗");
        return "[" + doneString + "] " + this.name;
    }
}
