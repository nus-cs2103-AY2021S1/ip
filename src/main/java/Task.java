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

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        if (isDone) {
            System.out.println("__________________________________________________________");
            System.out.println("Task is already done!");
            System.out.println("__________________________________________________________\n");
        }
        this.isDone = true;
    }
    public String toString() {
        return '[' + getStatusIcon() + "] " + description;
    }
}
