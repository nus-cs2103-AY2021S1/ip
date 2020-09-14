package task;
/**
 * A abstract class of task.
 * Contains abstract methods to be overridden by sub classes.
 */
public abstract class Task {
    public abstract String getDescription();
    public abstract String getStatusIcon();
    public abstract Task markAsDone();
    public abstract String toStringOfDatabase();
}
