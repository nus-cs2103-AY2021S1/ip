import java.util.Objects;

public abstract class DukeTask {
    private final String description;
    private boolean isDone;

    public DukeTask(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean getDoneStatus() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DukeTask dukeTask = (DukeTask) o;
        return isDone == dukeTask.isDone &&
                description.equals(dukeTask.description);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        DukeTask clone = null;
        try {
            clone = (DukeTask) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return clone;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, isDone);
    }
}