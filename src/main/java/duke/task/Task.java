package main.java.duke.task;

import java.time.LocalDate;

/**
 * The Task class describes a task which can be stored in a task list.
 * The instance of the Task class has the description of the task and
 * the state of whether the task has been completed. There can be many types of task.
 */
public class Task {
    protected String description;
    protected boolean isCompleted;

    /**
     * Creates a task with the description of the task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }
    /*
    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }
    */

    /**
     * Changes the state of the task and marks it as completed.
     */
    public void markAsCompleted(){
        this.isCompleted = true;
    }

    /**
     * Returns the state of completion of the task.
     *
     * @return The state of completion of the task.
     */
    public String getState() {
        if(this.isCompleted) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    /**
     * Returns a representation of the task in terms of the description and state.
     *
     * @return The representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getState() + "] " + description;
    }

    /**
     * Returns a representation of the task in terms of the description and
     * state for the task to be stored into the record.
     *
     * @return The representation of the task in terms of the description and state.
     */
    public String record() {
        return "";
    }

    /**
     * Returns the boolean value that describes whether the task is on
     * the specific date.
     *
     * @param localDate The specific date.
     * @return The boolean value that describes whether the task is on
     * the specific date.
     */
    public boolean isAt(LocalDate localDate) {
        return false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

}
