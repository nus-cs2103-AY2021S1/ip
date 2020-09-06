package duke.task;

/**
 * Encapsulates a task for the Duke program. A task is inclusive of a textual description and a boolean flag to mark
 * whether it has been completed or not. There are several types of tasks, namely: to-do, event and deadline.
 */
public class Task {

    /** Describes the task */
    protected String description;

    /** Marks whether the task has been completed or not */
    protected boolean isDone;

    /** The symbol that represents the type of task */
    protected String taskTypeSymbol; // [T], [D] or [E]

    /** The name of the task type */
    protected String taskTypeName; // to-do, deadline or event

    /**
     * Creates and initializes a task.
     *
     * @param description The description of the task.
     * @param taskTypeSymbol The symbol that represents the type of task.
     * @param taskTypeName The name of the task type.
     * @param isDone Marks whether the task has been completed or not.
     */
    public Task(String description, String taskTypeSymbol, String taskTypeName, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.taskTypeSymbol = taskTypeSymbol;
        this.taskTypeName = taskTypeName;
    }

    /**
     * Indicates if the task is completed or not.
     *
     * @return "✓" if the task is completed; "✘" if it is not completed.
     */
    public String getStatusIconForGui() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getStatusIconForCli() {
        return (isDone ? "✓" : "✘");
    }

    /**
     * Changes the task's completion indicator to either completed or not completed.
     *
     * @param isDone The user enters true to mark the task as complete and false for incomplete.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the task's description.
     *
     * @return The task's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Formats the string that will be written in the save file to represent this particular task.
     *
     * @return The string that will be written in the save file to represent this particular task.
     */
    public String toStringForMemory() {
        return taskTypeSymbol + "|" + (isDone ? 1 : 0) + "|" + description;
    }

    /**
     * Formats the way the task is presented to the user as part of the task list.
     *
     * @return The String that represents the task when it is presented to the user as part of the task list.
     */
    public String toStringForGui() {
        return taskTypeSymbol + "[" + getStatusIconForGui() + "] " + description;
    }

    public String toStringForCli() {
        return taskTypeSymbol + "[" + getStatusIconForCli() + "] " + description;
    }
}
