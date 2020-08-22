public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone
                ? "✓"
                : "✘";
    }

    public void finishTask() { isDone = true; }

    @Override
    public String toString() {
        return getStatusIcon() + " > " + this.description;
    }


}
