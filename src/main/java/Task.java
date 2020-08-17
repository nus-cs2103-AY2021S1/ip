/**
 * The class to represent a task.
 */
public abstract class Task {
    private final String content;
    private boolean completed;

    Task(String content) {
        this.content = content;
        this.completed = false;
    }

    public String getContent() {
        return content;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean status) {
        completed = status;
    }

    protected String getCompleteMark() {
        return isCompleted() ? "✔︎" : "✘";
    }
}