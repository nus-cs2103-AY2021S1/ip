/** Task represents a task to be done.
 */
public class Task {
    String taskName;
    boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    // Returns a ticked check-box if done else cross
    public String toString() {
        return (this.isDone ? "[\u2713] " : "[\u2718] ") + this.taskName;
    }
}