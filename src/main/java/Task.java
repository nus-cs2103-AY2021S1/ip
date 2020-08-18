public class Task {
    String description;
    boolean done = false;

    public Task(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return this.done;
    }

    public Task done() {
        this.done = true;
        return this;
    }

    @Override
    public String toString() {
        String status = this.done ? "[✓]" : "[✗]";
        return status + " " + description;
    }

}
