public class Task {
    public String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    
    public String writeMessage() {
        return "";
    }

    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "✓" : "✗") + "] " + this.description;
    }
}
