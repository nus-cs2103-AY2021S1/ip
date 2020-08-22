public abstract class Task {
    protected String name;
    protected boolean done;

    Task(String name) {
        this.name = name;
        this.done = false;
    }

    Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public void markDone() {
        this.done = true;
    }

    public abstract String appendFile();

    @Override
    public String toString() {
        String doneString = (done == true ? "✓" : "✗");
        return "[" + doneString + "] " + this.name;
    }
}
