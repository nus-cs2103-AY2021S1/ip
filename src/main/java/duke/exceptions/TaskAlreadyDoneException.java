package duke.exceptions;

import duke.tasks.Task;

// Exception to check if task is already done
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
