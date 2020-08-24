import java.time.LocalDate;
import java.util.Optional;

abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public abstract Optional<LocalDate> getDate();

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean searchFound(String keyword) {
        return description.contains(keyword);
    }

    protected String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718"; //return tick or X symbols
    }

    @Override
    public String toString() {
        return getStatusIcon() + "|" + description;
    }
}