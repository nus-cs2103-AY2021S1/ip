package dobby.task;

/**
 * Parent class for all tasks
 */
public class Task {
    private String description;
    private boolean isDone;
    private String tag;


    Task(String description, String tag) {
        this.description = description;
        this.isDone = false;
        this.tag = tag;
    }

    /**
     * Returns the full description of the task object
     * @return description full description of the Task object
     */
    public String getDescription() {
        String checkbox = "[";

        if (this.isDone()) { // tick
            checkbox = checkbox + "\u2713] ";
        } else { // or cross
            checkbox = checkbox + "\u2718] ";
        }

        return checkbox + this.description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setDescription(String des) {
        this.description = des;
    }

    public String getTag() {
        return this.tag;
    }
}
