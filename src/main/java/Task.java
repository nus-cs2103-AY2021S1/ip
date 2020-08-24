public abstract class Task implements Encodable<Task> {

    protected String description;
    protected boolean completed;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public void setCompleted() {
        this.completed = true;
    }

    private String getStatusIcon() {
        return this.completed ? "\u2713" : "\u2718";
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
