package duke.exceptions;

import duke.tasks.Task;

/**
 * Represents the exception from the done function
 * when the task is already done.
 */
public class TaskAlreadyDoneException extends DukeException {
    public TaskAlreadyDoneException(Task task){

        super("This task is already done!\n" +
                task.toString());
    }

    @Override
    public String toString(){
        return "Error";
    }

}
