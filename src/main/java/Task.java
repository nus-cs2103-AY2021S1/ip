public class Task {
    private boolean done;
    private String name;

    Task(String name) {
        this.done = false; // new tasks are not done
        this.name = name;
    }


    public void markDone() {
        this.done = true;
    }

    public boolean isDone() {
        return this.done;
    }

    public String toString() {
        if (isDone()) {
            return "[✓] " + this.name;
        } else {
            return "[✗] " + this.name;
        }
    }
}
