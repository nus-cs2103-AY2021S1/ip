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

    /**
     * Changes the state of done to true.
     */
    public void markAsDone() {
        this.done = true;
    }

    /**
     * Changes the state of done to false
     */
    public void markAsUndone() {
        this.done = false;
    }

    /**
     * Returns the class in a String form that is suitable to be written to txt file
     * @return String to be written to user's txt file
     */
    public String toFileFormat() {
        String isDone = done ? "1" : "0";
        return isDone + " | " + name;
    }

    @Override
    public String toString() {
        String symbol = this.done ? "✓" : "✘";
        return "[" + symbol + "] " + this.name;
    }
}
