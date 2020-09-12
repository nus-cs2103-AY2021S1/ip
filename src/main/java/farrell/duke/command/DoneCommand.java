package main.java.farrell.duke.command;

import main.java.farrell.duke.DukeException;
import main.java.farrell.duke.task.TaskList;

public class DoneCommand extends Command {
    private int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
        type = CommandType.DONE;
    }

    @Override
    public String execute(TaskList taskList) throws DukeException {
        if (taskIndex <= 0 || taskIndex > taskList.getSize()) {
            throw new DukeException("There is no task with index " + taskIndex);
        }
        taskList.updateDone(taskIndex);
        return "Nice! I've marked the this as done.\n"
                + taskList.getTask(taskIndex).toString();
    }
}
