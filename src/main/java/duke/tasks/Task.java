package duke.tasks;

import java.util.Arrays;
import java.util.List;

/**
 * Task super class that has a description and boolean
 * to check if it is completed.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a new task.
     *
     * @param description of new task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        assert !description.isEmpty()
                : "description cannot be empty";
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getState() {
        return "";
    }

    public List<String> getWordsInTask() {
        return Arrays.asList(description.split(" "));
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }
}
