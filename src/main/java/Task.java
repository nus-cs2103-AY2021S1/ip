public class Task {
    private boolean isDone;
    private String type;
    private String todo;

    Task(String type, String todo, boolean isDone) {
        this.type = type;
        this.todo = todo;
        this.isDone = isDone;
    }

    public void setStatus(boolean newStatus) {
        isDone = newStatus;
    }

    public String toString() {
        return String.format("[%s][%s] %s", type, isDone ? "✓" : "✗", todo);
    }

    public String toDbString() {
        return String.format("%s|%s|%s", type, isDone, todo);
    }
}
