public class Task {
    private boolean done;
    private String details;

    public Task(String details) {
        this.done = false;
        this.details = details;
    }

    public void markAsDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        String s = this.done ? "[✓] " : "[✗] ";
        return s + this.details;
    }
}
