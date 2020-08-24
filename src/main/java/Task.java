import java.time.LocalDate;

public abstract class Task {
    protected final String task;
    protected final boolean isDone;

    protected Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    public Task(String task){
        this.task = task;
        this.isDone = false;
    }

    public abstract Task markDone();

    public abstract LocalDate getDate();

    @Override
    public String toString() {
        String symbol = isDone ? "[✓] " : "[✗] ";
        return String.format("%s %s", symbol, task);
    }
}