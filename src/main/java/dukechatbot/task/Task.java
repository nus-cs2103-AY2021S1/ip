package dukechatbot.task;

import dukechatbot.enums.TaskEnum;

import java.util.Arrays;

public class Task {

    private String title;

    private boolean isDone;

    private TaskEnum type;

    public Task(String title, TaskEnum type) {
        this.title = title;
        this.type = type;
        this.isDone = false;
    }

    public Task(String title, boolean isDone, TaskEnum type) {
        this.title = title;
        this.isDone = isDone;
        this.type = type;
    }

    /**
     * Marks the task done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Returns the unicode corresponding to isDone value.
     * 
     * @return unicode corresponding to isDone value.
     */
    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718");
    }
    
    /**
     * Returns the save format of the task.
     * 
     * @return Save format of the task.
     */
    public String getSaveFormat() {
        return String.format("%s | %s | %s", this.type.getTaskLetter(),
                this.isDone ? 1 : 0, this.title);
    }

    /**
     * Returns the String information of the task.
     * 
     * @return String information of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.type.getTaskLetter(),
                this.getStatusIcon(), this.title);
    }

    /**
     * Returns the isDone attribute.
     * 
     * @return the isDone attribute.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Checks if the title attribute contains any word that matches the given substring.
     * The check is case-insensitive.
     * 
     * @param substring
     * @return Boolean on whether attribute contains the substring as a word.
     */
    public boolean contains(String substring) {
        return Arrays.stream(this.title.split("\\s+"))
                .anyMatch(x -> x.toLowerCase().equals(substring.toLowerCase()));
    }
}
