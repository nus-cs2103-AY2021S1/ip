package Duke;

/**
 * Super class for all types of tasks.
 */
public class Task {
    public final static String TICK = "\u2713";
    public final static String CROSS = "\u2718";

    protected final String name;
    protected boolean isDone;

    /**
     * Constructor creates a task and initialises done to false.
     *
     * @param name name of Task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public boolean getDone() {
        return this.isDone;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Formats task into the data format.
     *
     * @return
     */
    public String getParsedData() {
        return String.valueOf(this.isDone) + "/" + this.name;
    }

    public String toString() {
        String symbol = isDone ? Task.TICK : Task.CROSS;
        return "[" + symbol + "] " + name;
    }
}

