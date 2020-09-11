package duke.command;

import duke.DukeException;
import duke.task.TaskList;

public class Done extends Instruction {


    public Done(TaskList taskList, String taskDescription) {
        super(taskList, taskDescription);
    }

    @Override
    public String execute() throws DukeException {
        int index = Integer.parseInt(taskDescription) - 1;
        return taskList.doneTask(index);
    }
}
