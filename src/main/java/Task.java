import java.time.LocalDateTime;

public abstract class Task {
    protected final String text;
    protected boolean done = false;

    public Task(String text) {
        this.text = text;
    }

    public void setDone() {
        this.done = true;
    }

    @Override
    abstract public String toString();

    abstract public String toCommand();

    abstract public boolean compareTime(LocalDateTime now, long hours);
}
