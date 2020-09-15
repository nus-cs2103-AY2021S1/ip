package duke;

import java.util.HashSet;

/**
 * Abstract class representing a task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone = false;
    protected HashSet<String> tagSet = new HashSet<>();

    /**
     * Constructor that initializes with description.
     *
     * @param description task info.
     */
    protected Task(String description) {
        this.description = description;
    }

    /**
     * Marks this task as done, and returns this task.
     *
     * @return this Task object.
     */
    protected Task setDone() {
        this.isDone = true;
        return this;
    }

    /**
     * Returns a text representation of object.
     * This is for purpose of storage in .txt file.
     *
     * @return String of .txt format
     */
    protected String getTxtFormat() {
        StringBuilder tags = new StringBuilder();
        for (String t: tagSet) {
            tags.append("#");
            tags.append(t);
            tags.append(" ");
        }
        return (this.isDone ? "1, " : "0, ") + this.description + tags.toString();
    }

    /**
     * Returns a string representation of object.
     *
     * @return String object.
     */
    @Override
    public String toString() {
        String status = (this.isDone) ? "[✓]" : "[✗]";
        StringBuilder tags = new StringBuilder();
        for (String t: tagSet) {
            tags.append("#");
            tags.append(t);
            tags.append(" ");
        }
        return status + " " + description + " " + tags.toString();
    }

    public boolean hasTag(String tag) {
        return this.tagSet.contains(tag);
    }

    /** Adds a tag to the task
     *
     * @param tag to be added
     * @return Task
     * @throws DukeException
     */
    public Task addTag(String tag) throws DukeException {
        if (this.tagSet.contains(tag)) {
            throw new DukeException("Tag already exist for task " + this.toString());
        }
        this.tagSet.add(tag);
        return this;
    }

    /** Removes a tag from the task
     *
     * @param tag to be removed
     * @return Task
     * @throws DukeException
     */
    public Task untag(String tag) {
        this.tagSet.remove(tag);
        return this;
    }

    public String getDescription() {
        return this.description;
    }

}
