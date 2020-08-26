package bot;

public class Task {
    protected String name;
    protected boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public String getName() {
        return this.name;
    }

    public void markAsDone() {
        this.done = true;
    }

    public void markAsUndone() {
        this.done = false;
    }

    public String toFileFormat() {
        String isDone = done ? "1" : "0";
        return isDone + " | " + name ;
    }

    @Override
    public String toString() {
        String symbol = this.done ? "✓" : "✘";
        return "[" + symbol + "] " + this.name;
    }
}
