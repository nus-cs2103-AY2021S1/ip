package command;

import duke.DukeException;
import task.TaskList;

public class Find extends Instruction{

    public Find(TaskList taskList, String taskDescription) {
        super(taskList, taskDescription);
    }

    @Override
    public String execute() throws DukeException {
        return taskList.findTask(taskDescription);
    }
}
