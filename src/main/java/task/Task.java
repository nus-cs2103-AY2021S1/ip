package task;

/**
 * Super class for all types of tasks.
 */
public class Task {
    public final static String TICK = "\u2713";
    public final static String CROSS = "\u2718";
    public final static String DELIMITER = "/";
    public final static String TAG_ICON = " #";

    protected final String name;
    protected boolean isDone;

    protected String tag;

    /**
     * Constructor creates a task and initialises done to false.
     *
     * @param name name of Task.
     */
    public Task(String name) {
        assert name.length()>0: "Invalid Input";
        this.name = name;
        this.isDone = false;
        this.tag = "";
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
        return String.valueOf(this.isDone) + Task.DELIMITER + this.name;
    }

    public String toString() {
        String symbol = isDone ? Task.TICK : Task.CROSS;
        return "[" + symbol + "] " + name;
    }

    public void setTag (String tagName) {
        this.tag = tagName;
    }
}

