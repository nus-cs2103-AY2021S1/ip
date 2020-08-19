import javafx.util.Pair;
import java.util.ArrayList;
import java.util.List;

public class Task {
    protected int taskNum;
    protected String taskName;
    protected boolean isDone;

    public Task(int taskNum, String taskName) {
        this.taskNum = taskNum;
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ?  "\u2713" : "\u2718";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return String.format("%d. [%s] %s", taskNum, getStatusIcon(), taskName);
    }
}
