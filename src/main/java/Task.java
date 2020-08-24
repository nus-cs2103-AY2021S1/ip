import java.sql.SQLOutput;

/**
 * @author Damith C. Rajapakse
 * Reused from https://nus-cs2103-ay2021s1.github.io/website/schedule/week2/project.html with minor modifications
 */

public class Task {
    // @@author Damith C. Rajapakse
    // Reused from https://nus-cs2103-ay2021s1.github.io/website/schedule/week2/project.html with minor modifications
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() throws TaskCompletedException {
        if (isDone) {
            throw new TaskCompletedException();
        }
        this.isDone = true;
    }

    public String getDescription() {
        return description;
    }

    public boolean isTaskDone() {
        return isDone;
    }

    public String toString() {
        return '[' + getStatusIcon() + "] " + description;
    }
}
