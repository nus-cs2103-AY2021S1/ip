/**
 * Defines a generic type task.
 *
 * @author Kai Chao
 * @version 1.0
 * @since 26-08-2020
 */
public class Task {
    protected String description;
    protected boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : "✘"); //return tick or X symbols
    }

     public void markDone() {
        this.isDone = true;
     }

     @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description.trim();
     }

}
