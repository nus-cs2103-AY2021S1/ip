package duke.task;

/**
 * Represents a task.
 */
public class Task {
    public static final String TICK_ICON = "\u2713";
    public static final String CROSS_ICON = "\u2718";
    protected String desciption;
    protected boolean isDone;

    public Task(String description) {
        this.desciption = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? Task.TICK_ICON : Task.CROSS_ICON);
    }

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns true when the description contains certain keyword, false otherwise.
     * @param keyword Search key.
     * @return True when contains the search key and false otherwise.
     */
    public boolean containsKeyWord(String keyword) {
        return this.desciption.contains(keyword);
    }

    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Returns a string to be stored in data file.
     * @return Formatted string to be used in data file.
     */
    public String toFileStringFormat() {
        return String.format("%d / %s",isDone ? 1 : 0,this.desciption);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(),this.desciption);
    }
}
