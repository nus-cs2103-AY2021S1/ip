package duke.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import duke.util.DateTime;

/**
 * Wrapper class for all types of tasks.
 */
public class Task {

    private static final String TICK_SYMBOL = "\u2713";
    private static final String CROSS_SYMBOL = "\u2718";

    protected String taskType;
    protected String description;
    protected boolean isDone;
    protected DateTime dateTime;
    protected List<String> tags;

    /**
     * Creates a brand new {@code Task}.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new ArrayList<>();
    }


    public void markDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? TICK_SYMBOL : CROSS_SYMBOL);
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getTaskType() {
        return taskType;
    }

    public DateTime getDateTime() {
        return this.dateTime;
    }

    public boolean hasTags() {
        return tags != null && tags.size() != 0;
    }

    public List<String> getTags() {
        return tags;
    }

    public String tagsToString() {
        return hasTags() ? tags.toString().replace("[", "").replace("]", "") : "No tags yet";
    }

    /**
     * Adds tags to the task.
     * @param newTags Tags to be added.
     */
    public void addTags(List<String> newTags) {
        assert newTags != null : "Tags cannot be null.";
        tags.addAll(newTags);
    }

    /**
     * Processes serialized tag data and adds them to the task.
     * @param tags Tags to be added, in a serialized format.
     */
    public void addTagsFromData(String tags) {
        if (!tags.equals("No tags yet")) {
            List<String> tagsList = Arrays.asList(tags.split(", "));
            addTags(tagsList);
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }
}
