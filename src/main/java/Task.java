/**
 * Represents an Task reminder.
 */
public class Task {
    protected String description;
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

    /**
     * Generates the tick and cross symbol.
     *
     * @return String returns the string version of a tick and cross.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Generates the tag message.
     *
     * @return String returns the tage message with the # infront.
     */
    public String getTagDisplay() {
        String message = "";
        if (hasTag) {
            message += " #" + this.tagName;
        }
        return message;
    }

    /**
     * Generates the message of this task.
     *
     * @return String returns the message form of this task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description + getTagDisplay();
    }

    /**
     * Checks wether the keyword is in the descrition.
     *
     * @param substring the string to be found.
     * @return boolean if the keyword exists.
     */
    public boolean containsKeyword(String substring) {
        return this.description.contains(substring);
    }

    /**
     * Makes the tag with the current tag name.
     *
     * @param tagName Name of the tag.
     */
    public void makeTag(String tagName) {
        this.tagName = tagName;
        this.hasTag = true;
    }
}

