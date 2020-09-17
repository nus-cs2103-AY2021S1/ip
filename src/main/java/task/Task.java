package task;

/**
 * Super class for all types of tasks.
 */
public class Task {
    public static final char TICK = '\u2713';
    public static final char CROSS = '\u2718';
    public static final String DELIMITER = "/";
    public static final String TAG_ICON = " #";

    protected final String name;
    protected boolean isDone;
    protected String tag;

    /**
     * Constructor creates a task and initialises done to false.
     *
     * @param name name of Task.
     */
    public Task(String name) {
        assert name.length() > 0 : "Invalid Input";
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
     * @return Task as Parsed Format.
     */
    public String getParsedData() {
        return String.valueOf(this.isDone) + Task.DELIMITER + this.name;
    }

    public void setTag(String tagName) {
        this.tag = tagName;
    }

    @Override
    public String toString() {
        char symbol = isDone ? Task.TICK : Task.CROSS;
        return String.format("[%c] %s", symbol, name);
    }
}

