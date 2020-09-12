package main.java.farrell.duke.command;

import main.java.farrell.duke.DukeException;
import main.java.farrell.duke.task.TaskList;

public class ListCommand extends Command {

    public ListCommand() {
        type = CommandType.LIST;
    }

    @Override
    public String execute(TaskList taskList) throws DukeException {
        if (taskList.getSize() <= 0) {
            throw new DukeException("There are no tasks!");
        }
        return taskList.toString();
    }
}
