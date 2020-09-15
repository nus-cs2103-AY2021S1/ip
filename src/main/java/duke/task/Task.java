package duke.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import duke.exception.DukeException;
import duke.parser.DateTimeParser;
import duke.util.DateTime;

/**
 * Wrapper class for all types of tasks.
 */
public abstract class Task {

    private static final String TICK_SYMBOL = "\u2713";
    private static final String CROSS_SYMBOL = "\u2718";

    protected String taskType;
    protected String description;
    protected boolean isDone;
    protected DateTime dateTime;
    protected List<String> tags = new ArrayList<>();

    /**
     * Creates a brand new {@code Task}.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected Task(String[] data) throws DukeException {
        assert data.length == 4 || data.length == 5 : "Incorrect number of arguments in data.";
        this.taskType = data[0];
        this.isDone = Boolean.parseBoolean(data[1]);
        addTagsFromData(data[2]);
        this.description = data[3];

        if (data.length == 5) {
            this.dateTime = DateTimeParser.parse(data[4]);
        }
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
        return tags.size() != 0;
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
     * Remove a tag, if it exists.
     * @param tagToRemove Tag to be removed.
     * @return Whether the provided tag is removed from the list.
     */
    public boolean removeTag(String tagToRemove) {
        assert tagToRemove != null : "Tag to remove cannot be null.";
        return tags.remove(tagToRemove);
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
