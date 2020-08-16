public class Task {
    public boolean done;
    public String description;

    Task(String description) {
        this.description = description;
        this.done = false;
    }

    void done() {
        this.done = true;
    }

    @Override
    public String toString() {
        return this.done ? "[✓] " + this.description : "[✗] " + this.description;
    }
}
