package com.siawsam.duke;

import java.io.Serializable;

/**
 * Represents a serializable task.
 */
public class Task implements Serializable, Taggable {
    private final String taskDescription;
    private boolean isDone = false;
    private Tag tag;
    
    /**
     * Constructs a new Task instance containing a task description.
     *
     * @param description The task description.
     */
    public Task(String description) {
        taskDescription = description;
    }
    
    /**
     * Marks the task as "done".
     *
     * @return A response indicating the task that is marked as "done".
     */
    Response markAsDone() {
        isDone = true;
        return new Response(Ui.showMarkedAsDone(this));
    }
    
    /**
     * Checks if the task description includes a search term.
     *
     * @param keyword The search string.
     * @return True if the task description includes the keyword, false if otherwise.
     */
    public boolean includesKeyword(String keyword) {
        return taskDescription.contains(keyword);
    }
    
    public void addTag(Tag tag) {
        this.tag = tag;
    }
    
    Tag getTag() {
        return tag;
    }
    
    boolean isTagged() {
        return tag != null;
    }
    
    /**
     * Returns a string representation of the task.
     *
     * @return The formatted string.
     */
    public String toString() {
        String status = isDone ? "\u2713" : "\u2718";
        String taskString = "[" + status + "] " + taskDescription;
        if (tag == null) {
            return taskString;
        }
        return taskString + " (tag: #" + tag.getTagName() + ")";
    }
}
