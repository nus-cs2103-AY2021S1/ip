package duke.tasks;

/**
 * Represents tasks created by users.
 * To be inherited by Deadline, Event and ToDo classes.
 *
 */
public class Task {
    private boolean hasCompleted;
    private String taskName;
    private String tagName;
    private boolean isTagged;

    /**
     * Creates Task with relevant task name.
     * @param name Task Name.
     */
    public Task(String name) {
        this.taskName = name;
        this.hasCompleted = false;
        this.tagName = "#noTag";
        this.isTagged = false;
    }

    /**
     * Returns symbol corresponding to status of task.
     *
     * @return Current status of task;
     */
    protected String getStatus() {
        return (this.hasCompleted ? "Completed" : "Do Soon");
    }

    /**
     * Edits status of the task to completed.
     *
     */
    public void markDone() {
        this.hasCompleted = true;
    }

    /**
     * Returns task name of the selected task.
     *
     * @return Task name.
     */
    protected String getTaskName() {
        if (isTagged) {
            return this.taskName + " " + getTagName();
        }
        return this.taskName;
    }

    private String getTagName() {
        String retrievedTagName = "";
        if (this.isTagged) {
            retrievedTagName = this.tagName;
        }
        return retrievedTagName;
    }

    /**
     * Tags a task with the given tag word by user.
     *
     * @param tagWord Tag word to be used to tag task.
     */
    public void tagTask(String tagWord) {
        this.isTagged = true;
        this.tagName = "#" + tagWord;
    }

    /**
     * Returns String representation of task to be displayed in task list.
     *
     * @return Representation of task in task list.
     */
    @Override
    public String toString() {
        return "[" + getStatus() + "] " + getTaskName();
    }

}
