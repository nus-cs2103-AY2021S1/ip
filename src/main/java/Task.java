public class Task {
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

    public String appendFile() {
        String doneString = (done == true ? "1" : "0");
        return "" + " | " + doneString + " | " + this.name;
    }

    @Override
    public String toString() {
        String doneString = (done == true ? "✓" : "✗");
        return "[" + doneString + "] " + this.name;
    }
}
