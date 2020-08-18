public class Task {
    protected String description;
    protected boolean isComplete;

    public Task(String description) {
        this.description = description;
        isComplete = false;
    }

    protected Task(String description, boolean bool) {
        this.description = description;
        isComplete = bool;
    }

    public Task markDone() {
        return new Task(this.description, true);
    }

    @Override
    public String toString() {
        if (this.isComplete) {
            return "[T][✓] " + this.description;
        } else {
            return "[T][✗] " + this.description;
        }
    }
}
