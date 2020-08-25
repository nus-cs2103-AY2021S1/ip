/** Task represents a task to be done.
 */
public abstract class Task {
    String taskName;
    boolean isDone;

    Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    // Returns a ticked check-box if done else cross
    public String toString() {
        return (this.isDone ? "[✓] " : "[✘] ") + this.taskName;
    }

    public abstract String toFileFormat();
}