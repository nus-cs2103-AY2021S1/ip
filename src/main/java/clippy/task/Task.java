package clippy.task;

import clippy.exception.UpdateToDoTimeException;

/**
 * Represents a task that has to be done by the User, to be managed by Clippy.
 */
public abstract class Task {
    protected String desc;
    protected boolean isDone;
    protected TaskType taskType;
    
    private int index;
    
    private final String SYMBOL_TICK = "\u2713";
    private final String SYMBOL_CROSS = "\u2718";

    /**
     * Constructs a task object with done status initalised to false.
     * 
     * @param desc User-specified literal description of the task.
     */
    protected Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    /**
     * Sets list index of task to the given index.
     * 
     * @param index Index of task in the full list.
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Returns the index of the task in the full list.
     * 
     * @return Index of the task in the full list.
     */
    public int getTaskIndexInList() {
        return index;
    }
    
    private String getStatusIcon() {
        //return tick or X symbols
        return (isDone ? SYMBOL_TICK : SYMBOL_CROSS);
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Updates the description of the task to the given new description.
     * 
     * @param newDescription Literal new description of the task.
     */
    public void updateDescription(String newDescription) {
        this.desc = newDescription;
    }

    /**
     * Updates the time of the task to the given new time.
     *
     * @param newTime New time of the task.
     * @throws UpdateToDoTimeException If task is a todo as todos do not have a time aspect.
     */
    public abstract void updateTime(String newTime) throws UpdateToDoTimeException;

    /**
     * Generates a String encapsulating details of the task to be stored in the save file.
     * 
     * @return A String encapsulating details of the task to be stored in the save file.
     */
    public abstract String generateSaveFileData();

    /**
     * Returns literal description of the task.
     * 
     * @return Literal description of the task.
     */
    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        String statusIndicator = "[" + getStatusIcon() + "]";
        return statusIndicator + " " + desc;
    }
}
