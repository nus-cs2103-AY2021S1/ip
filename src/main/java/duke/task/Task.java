package duke.task;

/**
 * Encapsulates a task for the Duke program. A task is inclusive of a textual description and a boolean flag to mark
 * whether it has been completed or not. There are several types of tasks, namely: to-do, event and deadline.
 */
public class Task {

    /** Symbol for indicating that a task is completed, in the memory save file */
    public static final int DONE_SYMBOL_MEMORY = 1;

    /** Symbol for indicating that a task has not been completed, in the memory save file */
    public static final int NOT_DONE_SYMBOL_MEMORY = 0;

    private static final String DONE_SYMBOL_GUI = "\u2713";
    private static final String NOT_DONE_SYMBOL_GUI = "\u2718";
    private static final String DONE_SYMBOL_CLI = "✓";
    private static final String NOT_DONE_SYMBOL_CLI = "✘";

    /** Describes the task */
    protected String description;

    /** Additional information to describe the task */
    protected String additionalDescription;

    /** Additional information to describe the task to be stored in save file */
    protected String additionalDescriptionForMemory;

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
    public Task(String description, String additionalDescription, String additionalDescriptionForMemory,
                String taskTypeSymbol, String taskTypeName, boolean isDone) {
        this.description = description;
        this.additionalDescription = additionalDescription;
        this.additionalDescriptionForMemory = additionalDescriptionForMemory;
        this.isDone = isDone;
        this.taskTypeSymbol = taskTypeSymbol;
        this.taskTypeName = taskTypeName;
    }

    private String getStatusIconForGui() {
        return (isDone ? DONE_SYMBOL_GUI : NOT_DONE_SYMBOL_GUI);
    }

    private String getStatusIconForCli() {
        return (isDone ? DONE_SYMBOL_CLI : NOT_DONE_SYMBOL_CLI);
    }

    private int getStatusIconForMemory() {
        return (isDone ? DONE_SYMBOL_MEMORY : NOT_DONE_SYMBOL_MEMORY);
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
        return taskTypeSymbol + "|" + getStatusIconForMemory() + "|" + description + additionalDescriptionForMemory;
    }

    /**
     * Formats the way the task is presented to the user as part of the task list.
     *
     * @return The String that represents the task when it is presented to the user as part of the task list.
     */
    public String toStringForGui() {
        return taskTypeSymbol + "[" + getStatusIconForGui() + "] " + description + additionalDescription;
    }

    public String toStringForCli() {
        return taskTypeSymbol + "[" + getStatusIconForCli() + "] " + description + additionalDescription;
    }
}
