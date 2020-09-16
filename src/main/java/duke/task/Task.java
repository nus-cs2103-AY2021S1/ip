package duke.task;

import duke.storage.PrintSummary;

/**
 * Encapsulates a Task with a description.
 *
 * Task can be completed and will be mark as done. By default, it is initialised as incomplete.
 */
public abstract class Task implements PrintSummary {

    /** summary symbol for separating components */
    public static final String SYMBOL_SEPARATOR = "|";

    /** summary symbol for representing the task is complete */
    protected static final String SYMBOL_DONE = "1";
    /** summary symbol for representing the task is not complete */
    protected static final String SYMBOL_NOT_DONE = "0";

    /** description of the task */
    private String description;
    /** boolean flag indicating completion of the task */
    private boolean isDone;

    /**
     * Creates a new incomplete Task object.
     *
     * @param description description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a tick or cross String depending on whether the task is completed.
     *
     * @return tick if the task is completed, a cross otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Returns a String representation of the Task.
     *
     * @return String representing the task, showing its status and description.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * Returns the task description.
     *
     * @return string of task description.
     */
    public String getTaskDescription() {
        return description;
    }

    /**
     * Returns the state of th:we Task, whether it is completed or not.
     *
     * @return true if the task is completed, false otherwise.
     */
    public boolean isCompleted() {
        return isDone;
    }

    /**
     * Marks the task as complete.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Returns a summary of the Task.
     * Implementing classes should also implement a static method to reconstruct the object from the summary.
     *
     * @return string summarising the object.
     */
    public String getSummary() {
        String[] details = new String[] {
                getSymbol(),
                isCompleted() ? SYMBOL_DONE : SYMBOL_NOT_DONE,
                getTaskDescription()
        };
        return String.join(SYMBOL_SEPARATOR, details);
    }

    /** Returns true if the symbol provided is a valid save symbol for a save summary */
    protected static boolean isValidSaveSymbol(String symbol) {
        return symbol.equals(SYMBOL_DONE) || symbol.equals(SYMBOL_NOT_DONE);
    }

    /**
     * Returns a symbol representing the type of task.
     *
     * @return string containing a symbol for that type of task.
     */
    public abstract String getSymbol();


}
