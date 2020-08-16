public class Task {
    private final String content;

    public Task(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }
}
