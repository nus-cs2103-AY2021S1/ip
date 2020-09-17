package tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents an abstract task, consisting of a description.
 */
public abstract class Task {

    private final String description;
    private boolean isDone;
    private List<String> tags = new ArrayList<>();

    /**
     * Constructs a task given a description, setting default done status as false.
     * @param description provided for the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a task given a description and done status.
     * @param description provided for the task
     * @param isDone provided for the task
     */
    public Task(String description, boolean isDone, String tags) {
        this.description = description;
        this.isDone = isDone;
        String[] sepTags = tags.split(" ");
        this.tags.addAll(Arrays.asList(sepTags));
    }

    /**
     * Obtains the description of the task.
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status icon that represents the done status of a task.
     *
     * @return the status icon, tick meaning done
     * while cross meaning not done yet
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the string that represents the task.
     *
     * @return the string consisting of the done status and description
     */
    public String toString() {
        String tags = createTagString();
        return tags.equals("")
                ? "[" + getStatusIcon() + "] " + this.description
                : "[" + getStatusIcon() + "] " + this.description
                        + " (" + tags.substring(0, tags.length() - 1) + ")";
    }

    private String createTagString() {
        StringBuilder tags = new StringBuilder();
        for (String tag : this.tags) {
            if (tag == null || tag.equals("")) {
                continue;
            }
            tags.append("#").append(tag).append(" ");
        }
        return tags.toString();
    }

    /**
     * Returns the string that represents the task in a database.
     *
     * @return the string consisting of the done status and description
     */
    public String databaseString() {
        String doneStatus = this.isDone ? "true" : "false";
        String tags = createTagsDataString();
        return doneStatus + " | " + this.description + " | " + tags;
    }

    private String createTagsDataString() {
        StringBuilder tags = new StringBuilder();
        for (String tag : this.tags) {
            if (tag == null || tag.equals("")) {
                continue;
            }
            tags.append(tag).append(" ");
        }
        return tags.toString();
    }

    /**
     * Adds a tag to the task.
     * @param tagName as provided
     */
    public void tagTask(String tagName) {
        this.tags.add(tagName);
    }

    /**
     * Searches a tag by the tag name.
     * @param name as specified tag name
     * @return true if tag contains a part of specified tag name, else false.
     */
    public boolean searchTag(String name) {
        for (String tag : this.tags) {
            if (tag.contains(name)) {
                return true;
            }
        }
        return false;
    }
}
