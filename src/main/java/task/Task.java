package task;

import util.Tag;

/**
 * Represents a Task, providing implementation of a general task.
 */
public class Task {
    /**
     * Description of the task.
     */
    protected String description;

    /**
     * State of whether the task is done.
     */
    protected boolean isDone;

    /**
     * Tag attached to the task.
     */
    protected Tag tag;

    /**
     * Creates a task.
     *
     * @param description Description of the task.
     * @param isDone      State of whether the task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Sets the tag for the task and returns the task.
     *
     * @param tag Tag to be tagged to task.
     * @return Return task.
     */
    public Task setTag(Tag tag) {
        this.tag = tag;
        return this;
    }

    /**
     * Remove the tag for the task and returns the task.
     *
     * @return Return task.
     */
    public Task removeTag() {
        this.tag = null;
        return this;
    }

    /**
     * Returns boolean indicating if task has been tagged.
     *
     * @return boolean indicating if task has beent tagged.
     */
    public boolean isTagged() {
        return tag != null;
    }

    /**
     * Returns a tick icon or cross icon based on done state of the task.
     *
     * @return Tick icon or cross icon.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Mark task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Return task description.
     *
     * @return task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Return a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        String task = "[" + this.getStatusIcon() + "]" + " " + this.description + " ";
        if (tag == null) {
            return task;
        } else {
            return task + "\n" + tag.toString();
        }
    }
}
