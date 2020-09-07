package duke;

import java.io.Serializable;

/**
 * Task class is a parent.
 */
public class Task implements Serializable {
    protected boolean isComplete;
    protected int index;
    protected String instructions;
    protected String tag;

    /**
     * Constructor for Task object with no tag.
     * @param isComplete Boolean to indicate if task is complete
     * @param index Index position of Task in TaskList
     * @param instructions Task description
     */
    public Task(boolean isComplete, int index, String instructions) {
        this.isComplete = isComplete;
        this.index = index;
        this.instructions = instructions;
        this.tag = null;
    }

    /**
     * Constructor for Task object with a tag.
     * @param isComplete Boolean to indicate if task is complete
     * @param index Index position of Task in TaskList
     * @param instructions Task description
     * @param tag Hashtag
     */
    public Task(boolean isComplete, int index, String instructions, String tag) {
        this.isComplete = isComplete;
        this.index = index;
        this.instructions = instructions;
        this.tag = tag;
    }

    /**
     * To change the status of a Task to done.
     * @return A completed Task object
     */
    public Task markDone() {
        if (this instanceof Todo) {
            return new Todo(true, this.index, this.instructions, this.tag);
        } else if (this instanceof Deadline) {
            return new Deadline(true, this.index, this.instructions, ((Deadline) this).getDate(), this.tag);
        } else if (this instanceof Event) {
            return new Event(true, this.index, this.instructions, ((Event) this).getTime(), this.tag);
        } else {
            return new Task(true, this.index, this.instructions, this.tag);
        }
    }

    /**
     * Prints the Task's tag if available.
     * @return A string containing the tag
     */
    public String printTag() {
        if (this.tag == null) {
            return "";
        } else {
            return " [" + this.tag + "]";
        }
    }

    @Override
    public String toString() {
        if (this.isComplete) {
            return "[✓] " + this.instructions + printTag();
        } else {
            return "[✗] " + this.instructions + printTag();
        }
    }
}
