package dobby.task;

public class Task {
    private final String description;
    private boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the full description of the task object
     * @return description full description of the Task object
     */
    public String getDescription() {
        String checkbox = "[";
        if (this.isDone()) {
            checkbox = checkbox + "\u2713] ";
        } else {
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
}
