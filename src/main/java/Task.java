import java.io.Serializable;

public class Task implements Serializable {

    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "✓" : "X") + "] " + this.description;
    }
}
