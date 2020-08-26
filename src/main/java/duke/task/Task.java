package duke.task;

public class Task {

    protected String description;
    protected boolean isDone;

    // Constructor for task
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Check if this task is done or not
    public boolean isDone() {
        return isDone;
    }

    // Mark this task as done
    public void markAsDone() {
        this.isDone = true;
    }

    // Return the appropriate icon for this task
    public String getIcon() {
        if (isDone) {
            return "V";
        } else {
            return "X";
        }
    }

    public String[] getInfo() {
        return new String[] {description};
    }

    // Return string representation of Task, added with tick or x symbol
    @Override
    public String toString() {
        return "[" + getIcon() + "] " + description;
    }
}
