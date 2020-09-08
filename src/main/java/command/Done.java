package command;

import duke.DukeException;
import task.TaskList;

public class Done extends Instruction {


    public Done(TaskList taskList, String taskDescription) {
        super(taskList, taskDescription);
    }

    @Override
    public String execute() throws DukeException {
        int index = Integer.parseInt(taskDescription) - 1;
        String output = taskList.doneTask(index);
        return output;
    }
}
