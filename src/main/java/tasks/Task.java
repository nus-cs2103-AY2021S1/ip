package tasks;

public class Task {
    private String title;
    private boolean isDone;
    private String type;

    public Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    public Task(String title, String type) {
        this.title = title;
        this.type = type;
        this.isDone = false;
    }

    public Task(String title, boolean isDone, String type) {
        this.title = title;
        this.isDone = isDone;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void markDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getSaveFormat() {
        return String.format("%s | %s | %s", type, isDone ? 1 : 0, title);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", type, this.getStatusIcon(), title);
    }

    public boolean getIsDone() {
        return isDone;
    }
}
