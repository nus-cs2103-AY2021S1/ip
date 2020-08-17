/**
 * The class to represent a task.
 */
public class Task {
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

    @Override
    public String toString() {
        String mark = completed ? "✔︎" : "✘";
        return String.format("[%s] %s", mark, content);
    }
}