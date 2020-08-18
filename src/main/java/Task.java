public class Task {
    private String name;
    private boolean done;

    Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void complete() {
        this.done = true;
    }

    @Override
    public String toString() {
        if (done) {
            return String.format("[✓] %s");
        } else {
            return String.format("[✗] %s");
        }
    }
}