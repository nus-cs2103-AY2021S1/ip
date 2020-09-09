package main.task;

import java.util.Arrays;

/**
 * Represents tasks.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.3
 * @since v0.1
 */
public class Task {
    private final String name;
    private final String[] tags;
    private boolean isDone;

    /**
     * Constructs a Task instance with the name of the task.
     * @param name the name of the task.
     * @param tags the tags associated with the task.
     */
    public Task(String name, String[] tags) {
        this.name = name;
        this.tags = tags;
        isDone = false;
    }

    /**
     * Constructs a Task instance with the name of the task
     * and the done state of the task.
     * @param name the name of the task.
     * @param doneState the done state of the task.
     * @param tags the tags associated with the task.
     */
    public Task(String name, boolean doneState, String[] tags) {
        this.name = name;
        this.tags = tags;
        isDone = doneState;
    }

    /**
     * Gets the name of the task.
     * @return the name of the task.
     */
    public String getName() {
        return name;
    }

    private String doneTag() {
        return isDone ? "[\u2713]" : "[\u2718]";
    }

    /**
     * Sets the done state of the task to true.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Gets the tags associated with the task.
     * @return tags associated with the task.
     */
    public String[] getTags() {
        return tags;
    }

    /**
     * Returns the string meant for writing to disk.
     * @return the string meant for writing to disk.
     */
    public String write() {
        String tags = String.join(";", this.tags);
        return String.format(",%d,%s,%s\n", isDone ? 1 : 0, tags, name);
    }

    @Override
    public String toString() {
        return String.format("%s %s", doneTag(), name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task o = (Task) obj;
            boolean isSameName = name.equals(o.name);
            boolean isSameDoneState = isDone == o.isDone;
            boolean isSameTags = Arrays.equals(tags, o.tags);

            return isSameName && isSameDoneState && isSameTags;
        }
        return false;
    }
}
