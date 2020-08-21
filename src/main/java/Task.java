import java.io.Serializable;

public class Task implements Serializable {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    @Override
    public String toString() {
        if (isDone) {
            return String.format("[✓] %s", name);
        } else {
            return String.format("[✗] %s", name);
        }
    }

    public void setDone() {
        this.isDone = true;
    }
}
