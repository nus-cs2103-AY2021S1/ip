public class Task {

    public String content;
    public boolean isComplete;

    public Task(String content) {
        this.content = content;
        this.isComplete = false;
    }

    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    @Override
    public String toString() {
        if (isComplete) {
            return String.format("[✓] %s", content);
        } else {
            return String.format("[✗] %s", content);
        }
    }
}
