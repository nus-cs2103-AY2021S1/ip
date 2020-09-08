package main.java.farrell.duke.command;

import main.java.farrell.duke.DukeException;
import main.java.farrell.duke.task.TaskList;

public class ByeCommand extends Command {
    public ByeCommand() {
        type = CommandType.BYE;
    }

    @Override
    public String execute(TaskList taskList) throws DukeException {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean shouldExit() {
        return true;
    }
}
