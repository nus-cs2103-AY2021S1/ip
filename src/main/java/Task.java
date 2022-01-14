public class Task {
    boolean completed;
    String name;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public void setDone() {
        this.completed = true;
    }

    @Override
    public String toString() {
        if (completed) {
            return "[✓] " + this.name;
        } else {
            return "[✗] " + this.name;
        }
    }

}
