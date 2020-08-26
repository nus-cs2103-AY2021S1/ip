import java.util.Date;

public abstract class Task {

    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718";
    }

    public abstract Date getDate();

    public void markAsDone() {
        this.isDone = true;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.name);
    }
}
