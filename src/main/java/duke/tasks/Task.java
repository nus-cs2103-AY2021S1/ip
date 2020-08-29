package duke.tasks;

import java.util.Arrays;
import java.util.List;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
