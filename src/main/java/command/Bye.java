package command;

import duke.DukeException;
import task.TaskList;

public class Bye extends Instruction {

    public Bye(TaskList taskList, String taskDescription) {
        super(taskList, taskDescription);
    }

    @Override
    public String execute() throws DukeException {
        taskList.setTaskListNotUpdating();
        return "Bye, Have a Great Time!";
    }
}
