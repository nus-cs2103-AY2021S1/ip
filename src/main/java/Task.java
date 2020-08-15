public class Task {

    String name;
    Boolean completed;

    Task(String name) {
        this.name = name;
        this.completed = false;
    }

    @Override
    public String toString() {
        if (completed) {
            return String.format("[✓] %s", name);
        } else {
            return String.format("[✗] %s", name);
        }
    }

    public void setCompleted() {
        completed = true;
    }
}
