public class Task {
    private String description;
    private boolean isComplete;

    public Task(String description) {
        this.description = description;
        isComplete = false;
    }

    private Task(String description, boolean bool) {
        this.description = description;
        isComplete = bool;
    }

    public String readDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isComplete;
    }

    public Task markDone() {
        return new Task(this.description, true);
    }
}
