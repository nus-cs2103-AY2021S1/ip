public class Task {
    public String task;
    public boolean done;

    public Task(String task) {
        this.task = task;
        this.done = false;
    }
    
    public String writeMessage() {
        return "";
    }

    public void markDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        return "[" + (this.done ? "✓" : "✗") + "] " + this.task;
    }
}
