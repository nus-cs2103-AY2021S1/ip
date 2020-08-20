package Task;

public class Task {
    private String content;
    private Boolean isDone;
    public Task(String content) {
        this.isDone = false;
        this.content = content;
    }
    public String returnStringForm() {
        return "[" + getStatusIcon() +"] " + this.content;
    }
    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718");
    }
    public void markAsDone() {
        this.isDone = true;
    }
}
