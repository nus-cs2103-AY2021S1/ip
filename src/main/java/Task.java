public class Task {
    private final String content;
    private boolean completed = false;

    public Task(String content) {
        this.content = content;
    }

    public void markAsDone() {
        completed = true;
    }

    @Override
    public String toString() {
        return (completed ? "[✓]" : "[✗]") + " " + content;
    }
}
