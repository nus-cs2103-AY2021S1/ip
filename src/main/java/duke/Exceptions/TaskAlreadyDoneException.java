package duke.Exceptions;

import duke.Tasks.Task;

// Exception to check if task is already done
public class TaskAlreadyDoneException extends DukeException {
    public TaskAlreadyDoneException(Task task){

        super("This task is already done!\n" +
                task.toString());
    }

    public String toString(){
        return "Error";
    }

}
