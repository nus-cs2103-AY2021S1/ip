public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public void checked() {
        this.isDone = true;
    }

    public boolean isCompleted() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public String getType() {
        return null;
    }

    public String getDate() {
        return null;
    }

    @Override
    public String toString() {
        String tick = "✔";
        String cross = "✘";
        if (isDone) {
            return "[" + tick + "] " + this.description;
        } else {
            return "[" + cross + "] " + this.description;
        }
    }
}
