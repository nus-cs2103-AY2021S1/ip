package duke.dependencies.executable;

import duke.dependencies.task.Task;


/**
 * Classes implementing this interface have a command type and can be interpreted by their type.
 * Task can be extracted as well as implementing classes serve as a wrapper for the Task.
 */
public interface Executable {

    /**
     * Returns the command type of the Executable object. Manipulation of todo list
     * is based on command type in conjunction with the Task contained.
     * @return command type
     */
    CommandType getType();

    /**
     * Returns the Task object wrapped within the Executable.
     * @return Task object
     */
    Task getTask();

}
