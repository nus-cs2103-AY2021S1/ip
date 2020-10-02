package duke.command;

/**
 * Types that a command can be.
 */
public enum CommandType {

    /**
     * User wants to add a task.
     */
    ADD,

    /**
     * User inputs the type of task.
     */
    TASKTYPE,

    /**
     * User inputs the task description.
     */
    TASK,

    /**
     * User wants to delete a task.
     */
    DELETE,

    /**
     * Delete task.
     */
    DELETETASK,

    /**
     * User wants to mark a task as done.
     */
    DONE,

    /**
     * Mark task.
     */
    DONETASK,

    /**
     * User wants to find a task.
     */
    FIND,

    /**
     * Find task.
     */
    FINDTASK,

    /**
     * User wants to know what commands there are.
     */
    HELP,

    /**
     * User wants to assign a priority level.
     */
    PRIORITY,

    /**
     * User inputs the task to be updated.
     */
    PRIORITYLEVEL,

    /**
     * Set priority of the task.
     */
    PRIORITYSET,

    /**
     * User wants to know what is in the task list.
     */
    LIST,

    /**
     * Reset for when an exception is encountered.
     */
    RESET,

    /**
     * Exit the program.
     */
    EXIT,
}
