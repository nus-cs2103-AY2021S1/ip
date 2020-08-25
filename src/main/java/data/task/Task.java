package data.task;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public abstract class Task {

    protected String description;
    protected boolean isDone;
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy hh:mm a");

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Retrieves a listing of every word in the description, in order.
     */
    public List<String> getWordsInDescription() {
        return Arrays.asList(description.split("\\s+"));
    }

    public String getStatusIcon() {
        return (isDone ? "O" : "X"); //return O or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public abstract String fileFormat();

    @Override
    public String toString() {
        return String.format("[%1$s] %2$s", this.getStatusIcon(), this.description);
    }

}

