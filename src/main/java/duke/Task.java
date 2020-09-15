package duke;

import java.util.ArrayList;

/**
 * Represents a Task with a specified description, and boolean to indicate if completed or not.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected ArrayList<Tag> tags = new ArrayList<>();

    /**
     * Constructor for the Task class
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns an "O" if task is completed, an "X" if otherwise.
     *
     * @return "O" if completed, "X" if not.
     */
    public String getStatusIcon() {
        return (isDone ? "O" : "X");
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns all the tags tagged to the task.
     *
     * @return The tasks tagged to the task.
     */
    public String getTags() {
        String response = "";
        for (Tag tag : tags) {
            response = response + "#" + tag + " ";
        }
        return response;
    }

    /**
     * Adds a tag to the task.
     */
    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    /**
     * Removes the specified tag from the task if it exists.
     *
     * @param tagString The specified tag.
     * @return Returns 1 if tag was found and removed, 0 if tag was not found.
     */
    public int removeTag(String tagString) {
        for (int i = 0; i < this.tags.size(); i++) {
            if (Tag.tagToString(this.tags.get(i)).equals(tagString)) {
                this.tags.remove(i);
                return 1;
            }
        }
        return 0;
    }

    /**
     * Sets the task to be completed.
     */
    public void setDone() {
        this.isDone = true;
    }

    public String formattedDateString() {
        return "";
    }

    public String formattedDateStringWithTags() {
        return "";
    }

    @Override
    public String toString() {
        String done = this.isDone ? "O" : "X";
        return "[" + done + "]" + " " + this.description;
    }
}
