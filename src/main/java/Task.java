/**
 * Represents an Task reminder.
 */
public class Task {
    /**
     * The description of the task.
     */
    protected String description;
    /**
     * If teh task is done.
     */
    protected boolean isDone;

    protected String tagName;

    protected boolean hasTag;

    /**
     * Constructs a task object.
     *
     * @param description The description of this task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.hasTag = false;
    }

    /**
     * Marks if the task is done.
     */
    public String markAsDone() {
        this.isDone = true;
        return UI.printMarkAsDone(this);
    }

    /**
     * Generates the string format for saving.
     *
     * @return String format fro saving.
     */
    public String writeSaveFormat() {
        String store = "D |";
        if (isDone) {
            store += " 1 |";
        } else {
            store += " 0 |";
        }
        store += " " + this.description;
        if (hasTag) {
            store += " | " + this.tagName;
        }
        return store;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getTagDisplay() {
        String message = "";
        if (hasTag) {
            message += " #" +  this.tagName;
        }
        return message;
    }
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description + getTagDisplay();
    }

    public boolean containsKeyword(String substring) {
        return this.description.contains(substring);
    }

    public void makeTag(String tagName) {
        this.tagName = tagName;
        this.hasTag = true;
    }
}

