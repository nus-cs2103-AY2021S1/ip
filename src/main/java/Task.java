import java.lang.reflect.Array;
import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;
    public static ArrayList<Task> taskList = new ArrayList<>();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public boolean getDone() {
        return isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String toString() {
        return "["
                + getStatusIcon()
                + "]"
                + " "
                + getDescription();
    }

    public String toFileString() {
        return getDescription();
    }
}