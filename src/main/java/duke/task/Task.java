package duke.task;

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Task) {
            Task t = (Task) o;
            return t.task.equals(this.task);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String symbol = isDone ? "[✓] " : "[✗] ";
        return String.format("%s %s", symbol, task);
    }

    public String saveFormat() {
        String isDoneString = isDone ? "1" : "0";
        return String.format("%s %s ", isDoneString, task);
    }
}