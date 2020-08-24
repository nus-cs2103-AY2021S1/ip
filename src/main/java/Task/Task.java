package Task;

public abstract class Task {
    protected String description;
    protected boolean completed;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public String toString() {
        String icon;
        if (completed) {
            icon = "[" + "\u2713" + "]";
        } else {
            icon = "[" + "\u2718" + "]";
        }
        return icon + " " + description;
    }

    public void setCompleted() {
        this.completed = true;
    }

    public abstract String toEncoding();
}
