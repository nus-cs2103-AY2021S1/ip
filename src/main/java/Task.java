public class Task {
    private final String contents;
    private boolean done = false;

    public Task(String contents) {
        this.contents = contents;
    }

    public void markDone() {
        done = true;
    }

    public String toString() {
        return (done ? "[✓]" : "[✗]") + " " + contents;
    }
}
