import java.io.Serializable;

public abstract class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getStatusIcon() {
        return (isDone ? "Y" : "X"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
