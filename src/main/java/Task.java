import java.io.Serializable;

public class Task implements Serializable {
    private final String taskDescription;
    private boolean isDone = false;

    Task(String description) {
        taskDescription = description;
    }

    void markAsDone() {
       this.isDone = true;
       Ui.showMarkedAsDone(this);
    }

    public String toString() {
        String status = isDone ? "\u2713" : "\u2718";
        return "[" + status + "] " + taskDescription;
    }
}
