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
     * Returns a tick if task is completed, a cross if otherwise.
     *
     * @return Tick if completed, cross if not.
     */
    public String getStatusIcon() {
        return (isDone ? "O" : "X");
    }

    public String getDescription() {
        return this.description;
    }

    public String getTags() {
        String response = "";
        for (Tag tag : tags) {
            response = response + "#" + tag + " ";
        }
        return response;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public int removeTag(String tagString) {
        for (int i = 0; i < this.tags.size(); i++) {
            if (Tag.TagToString(this.tags.get(i)).equals(tagString)) {
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
