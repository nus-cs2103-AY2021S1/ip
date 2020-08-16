public class Task {
    protected final String text;
    protected boolean done = false;

    public Task(String text) {
        this.text = text;
    }

    public void setDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        return done
                ? "[✓] " + text
                : "[✗] " + text;
    }
}
